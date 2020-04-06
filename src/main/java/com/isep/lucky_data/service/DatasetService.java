package com.isep.lucky_data.service;

import com.isep.lucky_data.converter.DatasetRequestToDatasetConverter;
import com.isep.lucky_data.converter.DatasetToDatasetResponseConverter;
import com.isep.lucky_data.exception.DatasetNotFoundException;
import com.isep.lucky_data.exception.FileStorageException;
import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.model.DatasetFile;
import com.isep.lucky_data.payload.request.DatasetRequest;
import com.isep.lucky_data.payload.response.DatasetResponse;
import com.isep.lucky_data.repository.DatasetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DatasetService {

    @Autowired
    private DatasetRepository datasetRepository;

    public Dataset storeFile(MultipartFile file, DatasetRequest datasetRequest) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DatasetRequestToDatasetConverter converter = new DatasetRequestToDatasetConverter();
            Dataset dataset = converter.convertFromEntity(datasetRequest);
            DatasetFile datasetFile = new DatasetFile(fileName, file.getContentType(), file.getBytes(), dataset);
            dataset.setDatasetFile(datasetFile);


            return datasetRepository.save(dataset);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DatasetFile getFile(Long datasetId) {
        return datasetRepository.findById(datasetId)
                .orElseThrow(() -> new FileStorageException("File not found with id " + datasetId)).getDatasetFile();
    }

    public Dataset getDataset(Long datasetId) {
        //TODO: initialize and/or increment the datasetConsultation
        return datasetRepository.findById(datasetId).orElseThrow(
                () -> new DatasetNotFoundException("The Dataset with id " + datasetId + " does not exists !"));

    }

    public List<Dataset> getAllDatasets() {
        return datasetRepository.findAll();

    }
}
