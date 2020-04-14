package com.isep.lucky_data.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DatasetConsultationKey implements Serializable {

    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @Column(name = "dataset_id", nullable = false)
    private Long datasetId;

    public DatasetConsultationKey() {}

    public DatasetConsultationKey(Long departmentId, Long datasetId) {
        this.departmentId = departmentId;
        this.datasetId = datasetId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }
}
