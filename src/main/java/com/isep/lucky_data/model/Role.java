package com.isep.lucky_data.model;

import com.isep.lucky_data.model.converter.RoleNameConverter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = RoleNameConverter.class)
    @NaturalId
    @Column(name = "role", nullable = false, unique = true)
    private RoleName role;

    public Role() {

    }

    public Role(RoleName role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }
}
