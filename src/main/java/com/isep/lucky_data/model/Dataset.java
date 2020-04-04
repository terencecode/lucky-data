package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Dataset {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "title", unique = true)
    @NotNull
    @NotBlank
    private String title;

    @Column(name = "description")
    @NotNull
    @NotBlank
    private String description;

    @Column(name = "source")
    @NotNull
    @NotBlank
    private String source;

    @Column(name = "latitude")
    @NotNull
    @NotBlank
    private Float latitude;

    @Column(name = "longitude")
    @NotNull
    @NotBlank
    private Float longitude;

    @Column(name = "startDate")
    @NotNull
    @NotBlank
    private Integer startDate;

    @Column(name = "endDate")
    @NotNull
    @NotBlank
    private Integer endDate;

    @Column(name = "tag")
    @NotNull
    @NotBlank
    private String tag;

    @Column(name = "data")
    @NotNull
    @NotBlank
    private String data;

    @Column(name = "downloads")
    @NotNull
    @NotBlank
    private Integer downloads;

    @OneToMany(mappedBy = "dataset")
    private DatasetConsultation datasetConsultation;
}
