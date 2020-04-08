package com.isep.lucky_data.controller;

import com.isep.lucky_data.converter.DatasetToDatasetResponseConverter;
import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.model.DatasetFile;
import com.isep.lucky_data.payload.request.DatasetRequest;
import com.isep.lucky_data.payload.response.DatasetResponse;
import com.isep.lucky_data.service.DatasetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/dataset")
public class DatasetController {

    private static final Logger logger = LoggerFactory.getLogger(DatasetController.class);

    @Autowired
    private DatasetService datasetService;

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @PostMapping("/upload")
    @ApiOperation(value = "Adds a dataset" , authorizations = {@Authorization(value = "JWT")})
    public DatasetResponse addDataset(@RequestParam("file") MultipartFile file, DatasetRequest datasetRequest) {
        Dataset dataset = datasetService.storeFile(file, datasetRequest);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(dataset.getId().toString())
                .toUriString();

        return new DatasetResponse(dataset.getTitle(), dataset.getDescription(), dataset.getSource(),
                dataset.getLatitude(), dataset.getLongitude(), dataset.getStartDate(), dataset.getEndDate(),
                dataset.getTag(), dataset.getDatasetFile().getName(), fileDownloadUri, file.getContentType(),
                file.getSize());
    }

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @GetMapping("/download/{datasetId}")
    @ApiOperation(value = "Download the dataset file" , authorizations = {@Authorization(value = "JWT")})
    public ResponseEntity<Resource> downloadDatasetFile(@PathVariable Long datasetId) {
        // Load file from database
        DatasetFile datasetFile = datasetService.getFile(datasetId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(datasetFile.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + datasetFile.getName() + "\"")
                .body(new ByteArrayResource(datasetFile.getData()));
    }

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @GetMapping("/{datasetId}")
    @ApiOperation(value = "Gets dataset info by id" , authorizations = {@Authorization(value = "JWT")})
    public ResponseEntity<DatasetResponse> getDatasetById(@PathVariable Long datasetId) {
        Dataset dataset = datasetService.getDataset(datasetId);
        DatasetToDatasetResponseConverter converter = new DatasetToDatasetResponseConverter();
        DatasetResponse response = converter.convertFromEntity(dataset);
        return ResponseEntity.ok(response);
    }

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @GetMapping("/datasets")
    @ApiOperation(value = "Gets all datasets infos" , authorizations = {@Authorization(value = "JWT")})
    public ResponseEntity<Collection<DatasetResponse>> getAllDatasets() {
        List<Dataset> datasets = datasetService.getAllDatasets();
        DatasetToDatasetResponseConverter converter = new DatasetToDatasetResponseConverter();
        Collection<DatasetResponse> responses = converter.createFromEntities(datasets);
        return ResponseEntity.ok(responses);
    }
}
