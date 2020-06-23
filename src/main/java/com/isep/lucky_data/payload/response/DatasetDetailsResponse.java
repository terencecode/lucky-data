package com.isep.lucky_data.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This is the response returned")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DatasetDetailsResponse {

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

    @ApiModelProperty(value = "The date (in timestamp milliseconds) the dataset relates to", example = "1586802308338", required = true, position = 5)
    private Long date;

    @ApiModelProperty(value = "The start date (in timestamp milliseconds) of the range covered by the dataset", example = "1586802308338", position = 6)
    private Long startDate;

    @ApiModelProperty(value = "The start date (in timestamp milliseconds) of the range covered by the dataset", example = "1586802484643", position = 7)
    private Long endDate;

    @ApiModelProperty(value = "The latitude coordinate of the dataset if it is a geolocated dataset", example = "48.8763", position = 8)
    private Float latitude;

    @ApiModelProperty(value = "The longitude coordinate of the dataset if it is a geolocated dataset", position = 9)
    private Float longitude;

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

    @ApiModelProperty(value = "Wether or not the dataset was uploaded by api", example = "true", required = true, position = 15)
    private boolean api;

    public DatasetDetailsResponse(Long id, String title, String description, String source, Long uploadedAt, Long date, Long startDate, Long endDate, Float latitude, Float longitude, String tag, Long downloads, boolean api) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.source = source;
        this.uploadedAt = uploadedAt;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tag = tag;
        this.downloads = downloads;
        this.api = api;
    }

    public DatasetDetailsResponse(Long id, String title, String description, String source, Long uploadedAt, Long date, Long startDate, Long endDate, Float latitude, Float longitude, String tag, String fileName, String contentType, Long size, Long downloads) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.source = source;
        this.uploadedAt = uploadedAt;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public boolean isApi() {
        return api;
    }

    public void setApi(boolean api) {
        this.api = api;
    }
}
