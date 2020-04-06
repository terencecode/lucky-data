package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class DatasetFile {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "type")
    @NotNull
    @NotBlank
    private String type;

    @Lob
    @Column(name="data")
    @NotNull
    @NotEmpty
    private byte[] data;

    @OneToOne(mappedBy = "datasetFile")
    @NotNull
    private Dataset dataset;

    public DatasetFile(){}

    public DatasetFile(@NotNull @NotBlank String name, @NotNull @NotBlank String type, @NotNull @NotEmpty byte[] data, @NotNull Dataset dataset) {
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
