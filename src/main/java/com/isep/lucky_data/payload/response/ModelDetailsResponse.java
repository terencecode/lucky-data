package com.isep.lucky_data.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This is the response returned")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ModelDetailsResponse {

    @ApiModelProperty(value = "The id of the dataset", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(value = "The title of the dataset", example = "Air Pollution in Seoul", required = true, position = 1)
    private String title;

    @ApiModelProperty(value = "The description of the dataset", example = "This dataset deals with air pollution measurement information in Seoul, South Korea.\n" +
            "Seoul Metropolitan Government provides many public data, including air pollution information, through the...", required = true, position = 2)
    private String description;

    @ApiModelProperty(value = "The source of the dataset", example = "Banque de France", required = true, position = 3)
    private String source;

    @ApiModelProperty(value = "The date (in timestamp milliseconds) of upload of this dataset", example = "1586802308338", required = true, position = 4)
    private Long uploadedAt;

    @ApiModelProperty(value = "The tag associated to the dataset", example = "Prêt bancaire", position = 10)
    private String tag;

    @ApiModelProperty(value = "The name of the file associated to the dataset", example = "correspondance-code-insee-code-postal.csv", required = true, position = 11)
    private String fileName;

    @ApiModelProperty(value = "The type of the file", example = "text/csv", required = true, position = 12)
    private String contentType;

    @ApiModelProperty(value = "The size in Bytes of the file", example = "5674159", required = true, position = 13)
    private Long size;

    @ApiModelProperty(value = "The number of downloads of the dataset file", example = "321", required = true, position = 14)
    private Long downloads;

    public ModelDetailsResponse(Long id, String title, String description, String source, Long uploadedAt, String tag, Long downloads) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.source = source;
        this.uploadedAt = uploadedAt;
        this.tag = tag;
        this.downloads = downloads;
    }

    public ModelDetailsResponse(Long id, String title, String description, String source, Long uploadedAt, String tag, String fileName, String contentType, Long size, Long downloads) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.source = source;
        this.uploadedAt = uploadedAt;
        this.tag = tag;
        this.fileName = fileName;
        this.contentType = contentType;
        this.size = size;
        this.downloads = downloads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Long uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }
}
