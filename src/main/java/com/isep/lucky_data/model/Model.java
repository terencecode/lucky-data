package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
public class Model {
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

    @Column(name = "tag")
    private String tag;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "model_file_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "model_file_foreign_key"))
    @NotNull
    private ModelFile modelFile;

    @Column(name = "downloads", nullable = false)
    @NotNull
    private Long downloads = 0L;

    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ModelConsultation> modelConsultations;

    public Model() {
    }

    public Model(@NotNull @NotBlank String title, @NotNull @Size(max = 5000) String description, @NotNull @NotBlank String source, @NotNull Date uploadedAt, String tag) {
        this.title = title;
        this.description = description;
        this.source = source;
        this.uploadedAt = uploadedAt;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ModelFile getModelFile() {
        return modelFile;
    }

    public void setModelFile(ModelFile modelFile) {
        this.modelFile = modelFile;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
    }

    public Set<ModelConsultation> getModelConsultations() {
        return modelConsultations;
    }

    public void setModelConsultations(Set<ModelConsultation> modelConsultations) {
        this.modelConsultations = modelConsultations;
    }
}
