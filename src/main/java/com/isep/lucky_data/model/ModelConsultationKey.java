package com.isep.lucky_data.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ModelConsultationKey implements Serializable {
    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @Column(name = "model_id", nullable = false)
    private Long modelId;

    public ModelConsultationKey() {
    }

    public ModelConsultationKey(Long departmentId, Long modelId) {
        this.departmentId = departmentId;
        this.modelId = modelId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
}
