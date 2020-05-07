package com.isep.lucky_data.service;

import com.isep.lucky_data.converter.DatasetRequestToDatasetConverter;
import com.isep.lucky_data.exception.DatasetNotFoundException;
import com.isep.lucky_data.exception.FileNotFoundException;
import com.isep.lucky_data.exception.FileStorageException;
import com.isep.lucky_data.model.*;
import com.isep.lucky_data.payload.request.DatasetRequest;
import com.isep.lucky_data.repository.DatasetConsultationRepository;
import com.isep.lucky_data.repository.DatasetFileRepository;
import com.isep.lucky_data.repository.DatasetRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class DatasetService {

    @Autowired
    private DatasetRepository datasetRepository;

    @Autowired
    private DatasetFileRepository datasetFileRepository;

    @Autowired
    private DatasetConsultationRepository datasetConsultationRepository;

    public Dataset storeFile(MultipartFile file, DatasetRequest datasetRequest, ApplicationUser user) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DatasetRequestToDatasetConverter converter = new DatasetRequestToDatasetConverter();
            Dataset dataset = converter.convertFromEntity(datasetRequest);
            dataset = datasetRepository.save(dataset);
            DatasetFile datasetFile = new DatasetFile(fileName, file.getContentType(), BlobProxy.generateProxy(file.getBytes()), dataset);
            datasetFileRepository.save(datasetFile);

            Department userDepartment = user.getDepartment();

            DatasetConsultation datasetConsultation = new DatasetConsultation(new DatasetConsultationKey(userDepartment.getId(), dataset.getId()), userDepartment, dataset);

            datasetConsultationRepository.save(datasetConsultation);

            return dataset;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DatasetFile getFile(Long datasetId) {
        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(
                () -> new DatasetNotFoundException("The Dataset with id " + datasetId + " does not exists !"));
        DatasetFile datasetFile = datasetFileRepository.findByDataset(dataset)
                .orElseThrow(() -> new FileStorageException("File not found with id " + datasetId));
        dataset.setDownloads(dataset.getDownloads() + 1);
        datasetRepository.save(dataset);
        return datasetFile;
    }

    @Transactional
    public byte[] getFileContent(DatasetFile datasetFile) {
        try {
            return datasetFile.getData().getBytes(1L, (int) datasetFile.getData().length());
        } catch (SQLException e) {
            throw new FileNotFoundException("The file for the dataset " + datasetFile.getDataset().getTitle() + " does not exists");
        }
    }

    @Transactional
    public Long getFileSize(DatasetFile datasetFile) {
        try {
            return datasetFile.getData().length();
        } catch (SQLException e) {
            throw new FileNotFoundException("The file for the dataset " + datasetFile.getDataset().getTitle() + " does not exists");
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

    public List<Dataset> getAllDatasets() {
        return datasetRepository.findAll();
    }
}
