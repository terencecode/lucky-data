package com.isep.lucky_data.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This is the response returned")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ModelResponse {

    @ApiModelProperty(value = "The id of the model", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(value = "The title of the model", example = "Air Pollution in Seoul", position = 1)
    private String title;

    @ApiModelProperty(value = "The description of the model", example = "This model deals with air pollution measurement information in Seoul, South Korea.\n" +
            "Seoul Metropolitan Government provides many public data, including air pollution information, through the...", position = 2)
    private String description;

    @ApiModelProperty(value = "The source of the model", example = "Banque de France", position = 3)
    private String source;

    @ApiModelProperty(value = "The tag associated to the model", example = "Prêt bancaire", position = 4)
    private String tag;

    @ApiModelProperty(value = "The date (in timestamp milliseconds) the model was uploaded at", example = "1586802308338", required = true, position = 5)
    private Long uploadedAt;

    @ApiModelProperty(value = "The number of downloads of the model file", example = "321", required = true, position = 6)
    private Long downloads;

    public ModelResponse(Long id, String title, String description, String source, String tag, Long uploadedAt, Long downloads) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.source = source;
        this.tag = tag;
        this.uploadedAt = uploadedAt;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Long uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }
}
