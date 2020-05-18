package com.isep.lucky_data.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "This is the response")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponse {

    @ApiModelProperty(value = "The first name of the user", example = "Steve", required = true, position = 0)
    private String firstName;

    @ApiModelProperty(value = "The last name of the user", example = "Jobs", required = true, position = 1)
    private String lastName;

    @ApiModelProperty(value = "The email of the user", example = "example@gmail.com", required = true, position = 2)
    private String email;

    @ApiModelProperty(value = "The department of the user", example = "Marketing", required = true, position = 2)
    private String department;

    @ApiModelProperty(value = "The roles of the user", example = "[ROLE_USER, ROLE_ADMIN]", required = true, position = 2)
    private List<String> role;

    public UserResponse(String firstName, String lastName, String email, String department, List<String> role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.role = role;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
