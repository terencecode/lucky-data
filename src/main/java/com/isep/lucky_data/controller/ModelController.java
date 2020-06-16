package com.isep.lucky_data.controller;

import com.isep.lucky_data.configuration.CurrentUser;
import com.isep.lucky_data.configuration.UserPrincipal;
import com.isep.lucky_data.converter.ModelToModelDetailsResponseConverter;
import com.isep.lucky_data.converter.ModelToModelResponseConverter;
import com.isep.lucky_data.model.ApplicationUser;
import com.isep.lucky_data.model.Model;
import com.isep.lucky_data.model.ModelFile;
import com.isep.lucky_data.payload.request.ModelRequest;
import com.isep.lucky_data.payload.response.ModelDetailsResponse;
import com.isep.lucky_data.payload.response.ModelResponse;
import com.isep.lucky_data.service.ModelService;
import com.isep.lucky_data.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.apache.tika.mime.MimeTypeException;
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

import javax.net.ssl.SSLException;
import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/model")
@Api(tags = "Model API")
public class ModelController {
    private static final Logger logger = LoggerFactory.getLogger(ModelController.class);

    @Autowired
    private ModelService modelService;

    @Autowired
    private UserService userService;

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @PostMapping("/upload/file")
    @ApiOperation(value = "Upload a model", authorizations = {@Authorization(value = "JWT")})
    public ResponseEntity<URI> addModel(@RequestParam("file") MultipartFile file, ModelRequest modelRequest, @CurrentUser UserPrincipal userPrincipal) {
        ApplicationUser user = userService.getCurrentUser(userPrincipal);
        Model model = modelService.addModelByFile(file, modelRequest, user);

        URI fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(model.getId().toString()).build().toUri();

        return ResponseEntity.created(fileDownloadUri).build();
    }

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @GetMapping("/download/{modelId}")
    @ApiOperation(value = "Download the model file", authorizations = {@Authorization(value = "JWT")})
    public ResponseEntity<Resource> downloadModelFile(@PathVariable Long modelId) {
        // Load file from database
        ModelFile modelFile = modelService.getFile(modelId);

        Resource byteArrayResource = new ByteArrayResource(modelService.getFileContent(modelFile, modelId));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(modelFile.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + modelFile.getName() + "\"")
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .body(byteArrayResource);
    }

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @GetMapping("/{modelId}")
    @ApiOperation(value = "Gets model info by id", authorizations = {@Authorization(value = "JWT")}, produces = "application/json")
    public ResponseEntity<ModelDetailsResponse> getModelById(@PathVariable Long modelId, @CurrentUser UserPrincipal userPrincipal) {
        ApplicationUser user = userService.getCurrentUser(userPrincipal);
        Model model = modelService.getModel(modelId, user);
        ModelToModelDetailsResponseConverter converter = new ModelToModelDetailsResponseConverter();
        ModelDetailsResponse response = converter.convertFromEntity(model);
        ModelFile modelFile = model.getModelFile();
        response.setFileName(modelFile.getName());
        response.setContentType(modelFile.getType());
        response.setSize(modelService.getFileSize(modelFile));
        return ResponseEntity.ok(response);
    }

    @Secured({"ROLE_USER", "ROLE_DATA_EXPERT", "ROLE_ADMIN"})
    @GetMapping("/models")
    @ApiOperation(value = "Gets all models infos", authorizations = {@Authorization(value = "JWT")}, produces = "application/json")
    public ResponseEntity<Collection<ModelResponse>> getAllModels(@RequestParam String orderBy) {
        List<Model> models = modelService.getAllModels(orderBy);
        ModelToModelResponseConverter converter = new ModelToModelResponseConverter();
        Collection<ModelResponse> responses = converter.createFromEntities(models);
        return ResponseEntity.ok(responses);
    }
}
