package com.isep.lucky_data.model;

import javax.persistence.*;

@Entity
public class DatasetConsultation {

    @EmbeddedId
    DatasetConsultationKey id;

    @ManyToOne
    @MapsId("departement_id")
    @JoinColumn(name = "departement_id")
    Departement departement;

    @ManyToOne
    @MapsId("dataset_id")
    @JoinColumn(name = "dataset_id")
    Dataset dataset;

    Long consultations;
}
