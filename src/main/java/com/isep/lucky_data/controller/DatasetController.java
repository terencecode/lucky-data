package com.isep.lucky_data.controller;

import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.model.DatasetFile;
import com.isep.lucky_data.payload.request.DatasetRequest;
import com.isep.lucky_data.payload.response.DatasetResponse;
import com.isep.lucky_data.service.DatasetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/dataset")
public class DatasetController {

    private static final Logger logger = LoggerFactory.getLogger(DatasetController.class);

    @Autowired
    private DatasetService datasetService;

    @PostMapping("/upload")
    public DatasetResponse uploadFile(@RequestParam("file") MultipartFile file, DatasetRequest datasetRequest) {
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

    @GetMapping("/download/{datasetId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long datasetId) {
        // Load file from database
        DatasetFile datasetFile = datasetService.getFile(datasetId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(datasetFile.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + datasetFile.getName() + "\"")
                .body(new ByteArrayResource(datasetFile.getData()));
    }
}
