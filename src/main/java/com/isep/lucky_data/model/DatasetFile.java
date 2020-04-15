package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

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
    //@Basic(fetch = FetchType.LAZY)
    private Blob data;


    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "dataset_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "dataset_foreign_key"))
    @NotNull
    private Dataset dataset;

    public DatasetFile(){}

    public DatasetFile(@NotNull @NotBlank String name, @NotNull @NotBlank String type, @NotNull Blob data, @NotNull Dataset dataset) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.dataset = dataset;
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

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }
}
