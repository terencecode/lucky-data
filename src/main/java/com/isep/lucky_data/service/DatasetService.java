package com.isep.lucky_data.service;

import com.isep.lucky_data.converter.DatasetAPIInfoToDatasetApiConverter;
import com.isep.lucky_data.converter.DatasetRequestToDatasetConverter;
import com.isep.lucky_data.exception.DatasetNotFoundException;
import com.isep.lucky_data.exception.FileNotFoundException;
import com.isep.lucky_data.exception.FileStorageException;
import com.isep.lucky_data.model.*;
import com.isep.lucky_data.payload.request.DatasetAPIInfo;
import com.isep.lucky_data.payload.request.DatasetAPIRequest;
import com.isep.lucky_data.payload.request.DatasetRequest;
import com.isep.lucky_data.repository.DatasetApiRepository;
import com.isep.lucky_data.repository.DatasetConsultationRepository;
import com.isep.lucky_data.repository.DatasetFileRepository;
import com.isep.lucky_data.repository.DatasetRepository;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.util.List;

@Service
public class DatasetService {

    @Autowired
    private DatasetRepository datasetRepository;

    @Autowired
    private DatasetFileRepository datasetFileRepository;

    @Autowired
    private DatasetApiRepository datasetApiRepository;

    @Autowired
    private DatasetConsultationRepository datasetConsultationRepository;

    public Dataset addDatasetByFile(MultipartFile file, DatasetRequest datasetRequest, ApplicationUser user) {
        DatasetRequestToDatasetConverter converter = new DatasetRequestToDatasetConverter();
        Dataset dataset = converter.convertFromEntity(datasetRequest);
        dataset.setDatasetFile(saveDatasetFile(file));
        dataset = datasetRepository.save(dataset);

        createDatasetConsultation(user, dataset);

        return dataset;
    }

    public Dataset addDatasetByAPI(DatasetAPIRequest datasetAPIRequest, ApplicationUser user) throws SSLException, MimeTypeException {
        DatasetRequestToDatasetConverter converter = new DatasetRequestToDatasetConverter();
        Dataset dataset = converter.convertFromEntity(datasetAPIRequest);

        DatasetAPIInfo datasetAPIInfo = datasetAPIRequest.getDatasetAPIInfo();

        ByteArrayResource response = makeApiCall(datasetAPIInfo);

        DatasetAPIInfoToDatasetApiConverter datasetAPIInfoToDatasetApiConverter = new DatasetAPIInfoToDatasetApiConverter();
        DatasetApi datasetApi = datasetApiRepository.save(datasetAPIInfoToDatasetApiConverter.convertFromEntity(datasetAPIInfo));
        dataset.setDatasetApi(datasetApi);

        TikaConfig config = TikaConfig.getDefaultConfig();
        MediaType mediaType = MediaType.parseMediaType(datasetAPIInfo.getContentType());
        MimeType mimeType = config.getMimeRepository().forName(mediaType.toString());
        String extension = mimeType.getExtension();
        DatasetFile datasetFile = new DatasetFile(dataset.getTitle() + extension, datasetAPIInfo.getContentType(), BlobProxy.generateProxy(response.getByteArray()));
        datasetFile = datasetFileRepository.save(datasetFile);
        dataset.setDatasetFile(datasetFile);

        dataset = datasetRepository.save(dataset);

        createDatasetConsultation(user, dataset);

        return dataset;
    }

    private DatasetFile saveDatasetFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            DatasetFile datasetFile = new DatasetFile(fileName, file.getContentType(), BlobProxy.generateProxy(file.getBytes()));
            return datasetFileRepository.save(datasetFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    private void createDatasetConsultation(ApplicationUser user, Dataset dataset) {
        Department userDepartment = user.getDepartment();
        DatasetConsultation datasetConsultation = new DatasetConsultation(new DatasetConsultationKey(userDepartment.getId(), dataset.getId()), userDepartment, dataset);
        datasetConsultationRepository.save(datasetConsultation);
    }

    private ByteArrayResource makeApiCall(DatasetAPIInfo datasetAPIInfo) throws SSLException {
        String url = datasetAPIInfo.getUrl();
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        TcpClient tcpClient = TcpClient.create().secure(sslProviderBuilder -> sslProviderBuilder.sslContext(sslContext));
        HttpClient httpClient = HttpClient.from(tcpClient);
        WebClient client = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).baseUrl(url).build();

        WebClient.RequestBodyUriSpec requestSpec = client.method(datasetAPIInfo.getMethod());
        if (datasetAPIInfo.getMethod().equals(HttpMethod.POST)) {
            if (datasetAPIInfo.getBody() != null) {
                MultiValueMap<String, Object> body = new LinkedMultiValueMap();
                datasetAPIInfo.getBody().entrySet().stream().forEach(entry -> body.add(entry.getKey(),entry.getValue()));
                requestSpec.body(BodyInserters.fromMultipartData(body));
            } else if (datasetAPIInfo.getFormData() != null) {
                MultiValueMap<String, String> formData = new LinkedMultiValueMap();
                datasetAPIInfo.getFormData().entrySet().stream().forEach(entry -> formData.add(entry.getKey(), entry.getValue().toString()));
                requestSpec.body(BodyInserters.fromFormData(formData));
            }
        } else if (datasetAPIInfo.getMethod().equals(HttpMethod.GET)) {
            if(datasetAPIInfo.getQueryParams() != null) {
                MultiValueMap<String, String> queryParams = new LinkedMultiValueMap();
                datasetAPIInfo.getQueryParams().entrySet().stream().forEach(entry -> queryParams.add(entry.getKey(), entry.getValue().toString()));
                requestSpec.uri(uriBuilder ->
                        datasetAPIInfo.getPathParams() != null ? uriBuilder.queryParams(queryParams).build(datasetAPIInfo.getPathParams()) : uriBuilder.queryParams(queryParams).build()
                );
            }
        }

        requestSpec.header("Authorization", datasetAPIInfo.getTokenName() + " " + datasetAPIInfo.getTokenValue());
        requestSpec.accept(MediaType.parseMediaType(datasetAPIInfo.getContentType()));

        return requestSpec.retrieve().bodyToMono(ByteArrayResource.class).block();
    }

    public DatasetFile getFile(Long datasetId) {
        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(
                () -> new DatasetNotFoundException("The Dataset with id " + datasetId + " does not exists !"));
        DatasetFile datasetFile = dataset.getDatasetFile();
        datasetRepository.save(dataset);
        return datasetFile;
    }

    @Transactional
    public byte[] getFileContent(DatasetFile datasetFile, Long datasetId) {
        try {
            Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(
                    () -> new DatasetNotFoundException("The Dataset with id " + datasetId + " does not exists !"));
            byte[] bytes = datasetFile.getData().getBytes(1L, (int) datasetFile.getData().length());
            dataset.setDownloads(dataset.getDownloads() + 1);
            return bytes;
        } catch (Exception e) {
            throw new FileNotFoundException("The file for the dataset " /*+ datasetFile.getDataset().getTitle() */+ " does not exists", e);
        }
    }

    @Transactional
    public Long getFileSize(DatasetFile datasetFile) {
        try {
            return datasetFile.getData().length();
        } catch (Exception e) {
            throw new FileNotFoundException("The file for the dataset " /*+ datasetFile.getDataset().getTitle() */+ " does not exists", e);
        }
    }

    public Dataset getDataset(Long datasetId, ApplicationUser user) {
        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(
                () -> new DatasetNotFoundException("The Dataset with id " + datasetId + " does not exists !"));
        DatasetConsultation datasetConsultation = datasetConsultationRepository.findById(new DatasetConsultationKey(user.getDepartment().getId(), dataset.getId()))
                .orElseGet(() -> new DatasetConsultation(new DatasetConsultationKey(user.getDepartment().getId(), dataset.getId()), user.getDepartment(), dataset));
        datasetConsultation.setConsultations(datasetConsultation.getConsultations() + 1);
        datasetConsultationRepository.save(datasetConsultation);
        return dataset;

    }

    public List<Dataset> getAllDatasets(String orderBy) {
        List<Dataset> datasets;
        switch (orderBy) {
            case "downloads desc":
                datasets = this.datasetRepository.findAllByOrderByDownloadsDesc();
                break;
            case "downloads asc":
                datasets = this.datasetRepository.findAllByOrderByDownloadsAsc();
                break;
            case "uploadedAt desc":
                datasets = this.datasetRepository.findAllByOrderByUploadedAtDesc();
                break;
            case "uploadedAt asc":
                datasets = this.datasetRepository.findAllByOrderByUploadedAtAsc();
                break;
            case "title asc":
                datasets = this.datasetRepository.findAllByOrderByTitleAsc();
                break;
            case "title desc":
                datasets = this.datasetRepository.findAllByOrderByTitleDesc();
                break;
            default:
                datasets = this.datasetRepository.findAll();
                break;
        }
        return datasets;
    }

    public void deleteDataset(Long idDataset){
        Dataset dataset = datasetRepository.findById(idDataset).orElseThrow(
                () -> new DatasetNotFoundException("The Dataset with id " + idDataset + " does not exists !"));
        Long datasedId = dataset.getId();
        DatasetApi datasedApi = dataset.getDatasetApi();
        DatasetFile datasetFile = dataset.getDatasetFile();

        if (datasedApi != null){
            datasetApiRepository.deleteById(datasedApi.getId());
        }
        datasetRepository.deleteById(datasedId);
        datasetConsultationRepository.deleteConsultation(datasedId);
        datasetFileRepository.deleteFile(datasetFile.getId());

    }
}
