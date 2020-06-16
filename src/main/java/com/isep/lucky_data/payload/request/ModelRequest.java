package com.isep.lucky_data.payload.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ModelRequest {

    @NotNull
    @NotBlank
    @ApiParam(value = "The title of the dataset", example = "Air Pollution in Seoul", required = true)
    @ApiModelProperty(value = "The title of the dataset", example = "Air Pollution in Seoul", required = true, position = 0)
    private String title;

    @NotNull
    @ApiParam(value = "The description of the dataset", example = "This dataset deals with air pollution measurement information in Seoul, South Korea.\n" +
            "Seoul Metropolitan Government provides many public data, including air pollution information, through the...", required = true)
    private String description;

    @NotNull
    @NotBlank
    @ApiParam(value = "The source of the dataset", example = "Banque de France", required = true)
    private String source;

    @ApiParam(value = "The tag associated to the dataset", example = "Prêt bancaire")
    private String tag;

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
}
