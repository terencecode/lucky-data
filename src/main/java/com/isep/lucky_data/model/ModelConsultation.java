package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ModelConsultation {
    @EmbeddedId
    private ModelConsultationKey id;

    @ManyToOne(optional = false)
    @MapsId("department_id")
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "department_foreign_key"))
    private Department department;

    @ManyToOne(optional = false)
    @MapsId("model_id")
    @JoinColumn(name = "model_id", foreignKey = @ForeignKey(name = "model_foreign_key"))
    private Model model;

    @Column(name = "consultations", nullable = false)
    @NotNull
    private Long consultations = 0L;

    public ModelConsultation() {
    }

    public ModelConsultation(ModelConsultationKey id, Department department, Model model) {
        this.id = id;
        this.department = department;
        this.model = model;
    }

    public ModelConsultationKey getId() {
        return id;
    }

    public void setId(ModelConsultationKey id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Long getConsultations() {
        return consultations;
    }

    public void setConsultations(Long consultations) {
        this.consultations = consultations;
    }
}
