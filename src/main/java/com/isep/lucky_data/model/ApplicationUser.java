package com.isep.lucky_data.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotNull
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotNull
    @NotBlank
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @Email
    @NotNull
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false)
    @Size(max = 100)
    @NotNull
    @NotBlank
    private String password;

    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "department_foreign_key"))
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_foreign_key")),
            inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "role_foreign_key")))
    private Set<Role> roles = new HashSet<>();

    public ApplicationUser(){}

    public ApplicationUser(@NotNull @NotBlank String firstName, @NotNull @NotBlank String lastName, @Email @NotNull @NotBlank String email, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
