package com.equipeor.isepu.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public enum HomeworkStatus {
    TO_DO("TO DO"),
    IN_PROGRESS("IN PROGRESS"),
    DONE("DONE");

    @Id
    private String status;

    HomeworkStatus(String status){
        this.status = status;
    }

    public String toString(){
        return status;
    }
}
