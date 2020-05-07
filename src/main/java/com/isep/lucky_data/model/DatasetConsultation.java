package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class DatasetConsultation {

    @EmbeddedId
    private DatasetConsultationKey id;

    @ManyToOne(optional = false)
    @MapsId("department_id")
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "department_foreign_key"))
    private Department department;

    @ManyToOne(optional = false)
    @MapsId("dataset_id")
    @JoinColumn(name = "dataset_id", foreignKey = @ForeignKey(name = "dataset_foreign_key"))
    private Dataset dataset;

    @Column(name = "consultations", nullable = false)
    @NotNull
    private Long consultations = 0L;

    public DatasetConsultation() {}

    public DatasetConsultation(DatasetConsultationKey id, Department department, Dataset dataset) {
        this.id = id;
        this.department = department;
        this.dataset = dataset;
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
