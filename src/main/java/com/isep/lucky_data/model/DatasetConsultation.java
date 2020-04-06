package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class DatasetConsultation {

    @EmbeddedId
    private DatasetConsultationKey id;

    @ManyToOne
    @MapsId("department_id")
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @MapsId("dataset_id")
    @JoinColumn(name = "dataset_id")
    private Dataset dataset;

    @Column(name = "consultations")
    @NotNull
    @NotBlank
    private Long consultations = 0L;

    public DatasetConsultation() {}

    public DatasetConsultation(DatasetConsultationKey id, Department department, Dataset dataset, @NotNull @NotBlank Long consultations) {
        this.id = id;
        this.department = department;
        this.dataset = dataset;
        this.consultations = consultations;
    }

    public DatasetConsultationKey getId() {
        return id;
    }

    public void setId(DatasetConsultationKey id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }

    public Long getConsultations() {
        return consultations;
    }

    public void setConsultations(Long consultations) {
        this.consultations = consultations;
    }
}
