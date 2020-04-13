package com.isep.lucky_data.controller;

import com.isep.lucky_data.configuration.CurrentUser;
import com.isep.lucky_data.configuration.UserPrincipal;
import com.isep.lucky_data.converter.DatasetToDatasetDetailsResponseConverter;
import com.isep.lucky_data.converter.DatasetToDatasetResponseConverter;
import com.isep.lucky_data.model.ApplicationUser;
import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.model.DatasetFile;
import com.isep.lucky_data.payload.request.DatasetRequest;
import com.isep.lucky_data.payload.response.DatasetDetailsResponse;
import com.isep.lucky_data.payload.response.DatasetResponse;
import com.isep.lucky_data.service.DatasetService;
import com.isep.lucky_data.service.UserService;
import io.swagger.annotations.Api;
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

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/dataset")
@Api(tags = "Dataset API")
public class DatasetController {

    private static final Logger logger = LoggerFactory.getLogger(DatasetController.class);

    @Autowired
    private DatasetService datasetService;

    @Autowired
    private UserService userService;

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @PostMapping("/upload")
    @ApiOperation(value = "Upload a dataset", authorizations = {@Authorization(value = "JWT")})
    public ResponseEntity<URI> addDataset(@RequestParam("file") MultipartFile file, DatasetRequest datasetRequest, @CurrentUser UserPrincipal userPrincipal) {
        ApplicationUser user = userService.getCurrentUser(userPrincipal);
        Dataset dataset = datasetService.storeFile(file, datasetRequest, user);

        URI fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(dataset.getId().toString()).build().toUri();

        return ResponseEntity.created(fileDownloadUri).build();
    }

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @GetMapping("/download/{datasetId}")
    @ApiOperation(value = "Download the dataset file", authorizations = {@Authorization(value = "JWT")})
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
    @ApiOperation(value = "Gets dataset info by id", authorizations = {@Authorization(value = "JWT")}, produces = "application/json")
    public ResponseEntity<DatasetDetailsResponse> getDatasetById(@PathVariable Long datasetId) {
        Dataset dataset = datasetService.getDataset(datasetId);
        DatasetToDatasetDetailsResponseConverter converter = new DatasetToDatasetDetailsResponseConverter();
        DatasetDetailsResponse response = converter.convertFromEntity(dataset);
        return ResponseEntity.ok(response);
    }

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @GetMapping("/datasets")
    @ApiOperation(value = "Gets all datasets infos", authorizations = {@Authorization(value = "JWT")}, produces = "application/json")
    public ResponseEntity<Collection<DatasetResponse>> getAllDatasets() {
        List<Dataset> datasets = datasetService.getAllDatasets();
        DatasetToDatasetResponseConverter converter = new DatasetToDatasetResponseConverter();
        Collection<DatasetResponse> responses = converter.createFromEntities(datasets);
        return ResponseEntity.ok(responses);
    }
}
