package com.isep.lucky_data.payload.request;

import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DatasetRequest {

    @NotNull
    @NotBlank
    @ApiParam(value = "The title of the dataset", example = "Air Pollution in Seoul", required = true)
    private String title;

    @NotNull
    @ApiParam(value = "The description of the dataset", example = "This dataset deals with air pollution measurement information in Seoul, South Korea.\n" +
            "Seoul Metropolitan Government provides many public data, including air pollution information, through the...", required = true)
    private String description;

    @NotNull
    @NotBlank
    @ApiParam(value = "The source of the dataset", example = "Banque de France", required = true)
    private String source;

    @ApiParam(value = "The date (in timestamp milliseconds) the dataset relates to", example = "1586802308338", required = true)
    private Long date;

    @ApiParam(value = "The start date (in timestamp milliseconds) of the range covered by the dataset", example = "1586802308338")
    private Long startDate;

    @ApiParam(value = "The start date (in timestamp milliseconds) of the range covered by the dataset", example = "1586802484643")
    private Long endDate;

    @ApiParam(value = "The latitude coordinate of the dataset if it is a geolocated dataset", example = "48.8763")
    private Float latitude;

    @ApiParam(value = "The longitude coordinate of the dataset if it is a geolocated dataset", example = "2.3183")
    private Float longitude;

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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
