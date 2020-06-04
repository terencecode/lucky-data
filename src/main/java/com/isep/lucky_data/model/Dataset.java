package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Dataset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true, nullable = false)
    @NotNull
    @NotBlank
    private String title;

    @Column(name = "description", nullable = false)
    @NotNull
    @Size(max = 5000)
    private String description;

    @Column(name = "source", nullable = false)
    @NotNull
    @NotBlank
    private String source;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "uploaded_at", nullable = false)
    @NotNull
    private Date uploadedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
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

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "dataset_file_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "dataset_file_foreign_key"))
    @NotNull
    private DatasetFile datasetFile;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "dataset_api_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "dataset_api_foreign_key"))
    private DatasetApi datasetApi;

    @Column(name = "downloads", nullable = false)
    @NotNull
    private Long downloads = 0L;

    @OneToMany(mappedBy = "dataset", fetch = FetchType.LAZY)
    private Set<DatasetConsultation> datasetConsultations;

    public Dataset() {}

    public Dataset(@NotNull @NotBlank String title, @NotNull String description, @NotNull @NotBlank String source) {
        this.title = title;
        this.description = description;
        this.source = source;
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

    public DatasetApi getDatasetApi() {
        return datasetApi;
    }

    public void setDatasetApi(DatasetApi datasetApi) {
        this.datasetApi = datasetApi;
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
