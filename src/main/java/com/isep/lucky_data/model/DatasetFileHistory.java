package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.Date;

@Entity
public class DatasetFileHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false)
    @NotNull
    private Date date;

    @Lob
    @Column(name="data", nullable = false)
    @NotNull
    @Basic(fetch = FetchType.LAZY)
    private Blob data;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("dataset_file_id")
    @JoinColumn(name = "dataset_file_id", foreignKey = @ForeignKey(name = "dataset_file_foreign_key"))
    private DatasetFile datasetFile;
}
