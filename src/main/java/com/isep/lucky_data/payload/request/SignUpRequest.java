package com.isep.lucky_data.payload.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel(value = "Request", description = "This is the request you should send")
public class SignUpRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    @ApiModelProperty(value = "The first name of the user", example = "Steve", required = true, position = 0)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 100)
    @ApiModelProperty(value = "The last name of the user", example = "Jobs", required = true, position = 1)
    private String lastName;

    @NotBlank
    @Size(max = 150)
    @Email
    @ApiModelProperty(value = "The email of the user", example = "example@gmail.com", required = true, position = 2)
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)
    @ApiModelProperty(value = "The password of the user", example = "MySuperS3cretPassword!", required = true, position = 3)
    private String password;

    @NotBlank
    @ApiModelProperty(value = "The department where the user is working", example = "Finances", required = true, position = 4)
    private String departmentName;

    @NotBlank
    @ApiModelProperty(value = "The role of the user", example = "user or admin or data_expert", required = true, allowableValues = "user,admin,data_expert", position = 5)
    private String roleName;

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
