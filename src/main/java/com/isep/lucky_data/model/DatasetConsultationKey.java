package com.isep.lucky_data.model;

import javax.persistence.Column;

public class DatasetConsultationKey {

    @Column(name = "departement_id")
    Long departementId;

    @Column(name = "dataset_id")
    Long datasetId;
}
