package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.List;

@Entity
public class DatasetFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "type", nullable = false)
    @NotNull
    @NotBlank
    private String type;

    @Lob
    @Column(name="data", nullable = false)
    @NotNull
    @Basic(fetch = FetchType.LAZY)
    private Blob data;

    @OneToMany(mappedBy = "datasetFile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DatasetFileHistory> datasetFileHistories;

    public DatasetFile(){}

    public DatasetFile(@NotNull @NotBlank String name, @NotNull @NotBlank String type, @NotNull Blob data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Blob getData() {
        return data;
    }

    public void setData(Blob data) {
        this.data = data;
    }

    public List<DatasetFileHistory> getDatasetFileHistories() {
        return datasetFileHistories;
    }

    public void setDatasetFileHistories(List<DatasetFileHistory> datasetFileHistories) {
        this.datasetFileHistories = datasetFileHistories;
    }
}
