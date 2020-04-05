package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Dataset {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true)
    @NotNull
    @NotBlank
    private String title;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "source")
    @NotNull
    @NotBlank
    private String source;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "startDate")
    private Integer startDate;

    @Column(name = "endDate")
    private Integer endDate;

    @Column(name = "tag")
    private String tag;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dataset_file_id", referencedColumnName = "id")
    private DatasetFile datasetFile;

    @Column(name = "downloads")
    @NotNull
    private Integer downloads;

    @OneToMany(mappedBy = "dataset")
    private Set<DatasetConsultation> datasetConsultations;

    public Dataset() {}

    public Dataset(@NotNull @NotBlank String title, @NotNull String description, @NotNull @NotBlank String source, @NotNull DatasetFile datasetFile, @NotNull Integer downloads) {
        this.title = title;
        this.description = description;
        this.source = source;
        this.datasetFile = datasetFile;
        this.downloads = downloads;
    }

    public Dataset(@NotNull @NotBlank String title, @NotNull String description, @NotNull @NotBlank String source, Float latitude, Float longitude, Integer startDate, Integer endDate, String tag, @NotNull DatasetFile datasetFile, @NotNull @NotBlank Integer downloads, Set<DatasetConsultation> datasetConsultations) {
        this.title = title;
        this.description = description;
        this.source = source;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tag = tag;
        this.datasetFile = datasetFile;
        this.downloads = downloads;
        this.datasetConsultations = datasetConsultations;
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

    public Integer getStartDate() {
        return startDate;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public DatasetFile getDatasetFile() {
        return datasetFile;
    }

    public void setDatasetFile(DatasetFile datasetFile) {
        this.datasetFile = datasetFile;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Set<DatasetConsultation> getDatasetConsultations() {
        return datasetConsultations;
    }

    public void setDatasetConsultations(Set<DatasetConsultation> datasetConsultations) {
        this.datasetConsultations = datasetConsultations;
    }
}
