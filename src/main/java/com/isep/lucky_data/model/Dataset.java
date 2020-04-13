package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "uploaded_at")
    @NotNull
    private Date uploadedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    @NotNull
    private Date date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;


    @Column(name = "tag")
    private String tag;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dataset_file_id", referencedColumnName = "id")
    private DatasetFile datasetFile;

    @Column(name = "downloads")
    @NotNull
    private Long downloads = 0L;

    @OneToMany(mappedBy = "dataset")
    private Set<DatasetConsultation> datasetConsultations;

    public Dataset() {}

    public Dataset(@NotNull @NotBlank String title, @NotNull String description, @NotNull @NotBlank String source, @NotNull DatasetFile datasetFile) {
        this.title = title;
        this.description = description;
        this.source = source;
        this.datasetFile = datasetFile;
    }

    public Dataset(@NotNull @NotBlank String title, @NotNull String description, @NotNull @NotBlank String source, @NotNull Date uploadedAt, @NotNull Date date, Date startDate, Date endDate, Float latitude, Float longitude, String tag) {
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
    }

    public Long getId() {
        return id;
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

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }

    public Set<DatasetConsultation> getDatasetConsultations() {
        return datasetConsultations;
    }

    public void setDatasetConsultations(Set<DatasetConsultation> datasetConsultations) {
        this.datasetConsultations = datasetConsultations;
    }
}
