package com.isep.lucky_data.service;

import com.isep.lucky_data.converter.ModelRequestToModelConverter;
import com.isep.lucky_data.exception.ModelNotFoundException;
import com.isep.lucky_data.exception.FileNotFoundException;
import com.isep.lucky_data.exception.FileStorageException;
import com.isep.lucky_data.model.*;
import com.isep.lucky_data.payload.request.ModelRequest;
import com.isep.lucky_data.repository.*;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelFileRepository modelFileRepository;

    @Autowired
    private ModelConsultationRepository modelConsultationRepository;

    public Model addModelByFile(MultipartFile file, ModelRequest modelRequest, ApplicationUser user) {
        ModelRequestToModelConverter converter = new ModelRequestToModelConverter();
        Model model = converter.convertFromEntity(modelRequest);
        model.setModelFile(saveModelFile(file));
        model = modelRepository.save(model);

        createModelConsultation(user, model);

        return model;
    }

    private ModelFile saveModelFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            ModelFile modelFile = new ModelFile(fileName, file.getContentType(), BlobProxy.generateProxy(file.getBytes()));
            return modelFileRepository.save(modelFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    private void createModelConsultation(ApplicationUser user, Model model) {
        Department userDepartment = user.getDepartment();
        ModelConsultation modelConsultation = new ModelConsultation(new ModelConsultationKey(userDepartment.getId(), model.getId()), userDepartment, model);
        modelConsultationRepository.save(modelConsultation);
    }

    public ModelFile getFile(Long modelId) {
        Model model = modelRepository.findById(modelId).orElseThrow(
                () -> new ModelNotFoundException("The Model with id " + modelId + " does not exists !"));
        ModelFile modelFile = model.getModelFile();
        modelRepository.save(model);
        return modelFile;
    }

    @Transactional
    public byte[] getFileContent(ModelFile modelFile, Long modelId) {
        try {
            Model model = modelRepository.findById(modelId).orElseThrow(
                    () -> new ModelNotFoundException("The Model with id " + modelId + " does not exists !"));
            byte[] bytes = modelFile.getData().getBytes(1L, (int) modelFile.getData().length());
            model.setDownloads(model.getDownloads() + 1);
            return bytes;
        } catch (Exception e) {
            throw new FileNotFoundException("The file for the model " /*+ modelFile.getModel().getTitle() */+ " does not exists", e);
        }
    }

    @Transactional
    public Long getFileSize(ModelFile modelFile) {
        try {
            return modelFile.getData().length();
        } catch (Exception e) {
            throw new FileNotFoundException("The file for the model " /*+ modelFile.getModel().getTitle() */+ " does not exists", e);
        }
    }

    public Model getModel(Long modelId, ApplicationUser user) {
        Model model = modelRepository.findById(modelId).orElseThrow(
                () -> new ModelNotFoundException("The Model with id " + modelId + " does not exists !"));
        ModelConsultation modelConsultation = modelConsultationRepository.findById(new ModelConsultationKey(user.getDepartment().getId(), model.getId()))
                .orElseGet(() -> new ModelConsultation(new ModelConsultationKey(user.getDepartment().getId(), model.getId()), user.getDepartment(), model));
        modelConsultation.setConsultations(modelConsultation.getConsultations() + 1);
        modelConsultationRepository.save(modelConsultation);
        return model;

    }

    public List<Model> getAllModels(String orderBy) {
        List<Model> models;
        switch (orderBy) {
            case "downloads desc":
                models = this.modelRepository.findAllByOrderByDownloadsDesc();
                break;
            case "downloads asc":
                models = this.modelRepository.findAllByOrderByDownloadsAsc();
                break;
            case "uploadedAt desc":
                models = this.modelRepository.findAllByOrderByUploadedAtDesc();
                break;
            case "uploadedAt asc":
                models = this.modelRepository.findAllByOrderByUploadedAtAsc();
                break;
            case "title asc":
                models = this.modelRepository.findAllByOrderByTitleAsc();
                break;
            case "title desc":
                models = this.modelRepository.findAllByOrderByTitleDesc();
                break;
            default:
                models = this.modelRepository.findAll();
                break;
        }
        return models;
    }
}
