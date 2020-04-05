package com.isep.lucky_data.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Department {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    @NotNull
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "department")
    private Set<ApplicationUser> applicationUsers;

    @OneToMany(mappedBy = "department")
    private Set<DatasetConsultation> datasetConsultations;

    public Department(){}

    public Department(@NotNull @NotBlank String name) {
        this.name = name;
    }

    public Department(@NotNull @NotBlank String name, Set<ApplicationUser> applicationUsers, Set<DatasetConsultation> datasetConsultations) {
        this.name = name;
        this.applicationUsers = applicationUsers;
        this.datasetConsultations = datasetConsultations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ApplicationUser> getApplicationUsers() {
        return applicationUsers;
    }

    public void setApplicationUsers(Set<ApplicationUser> applicationUsers) {
        this.applicationUsers = applicationUsers;
    }

    public Set<DatasetConsultation> getDatasetConsultations() {
        return datasetConsultations;
    }

    public void setDatasetConsultations(Set<DatasetConsultation> datasetConsultations) {
        this.datasetConsultations = datasetConsultations;
    }
}
