package com.isep.lucky_data.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Departement {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users;

    @OneToMany(mappedBy = "departement")
    private DatasetConsultation datasetConsultation;

    public Departement(){}

    public Departement(String name, Set<User> users, DatasetConsultation datasetConsultation) {
        this.name = name;
        this.users = users;
        this.datasetConsultation = datasetConsultation;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public DatasetConsultation getDatasetConsultation() {
        return datasetConsultation;
    }

    public void setDatasetConsultation(DatasetConsultation datasetConsultation) {
        this.datasetConsultation = datasetConsultation;
    }
}
