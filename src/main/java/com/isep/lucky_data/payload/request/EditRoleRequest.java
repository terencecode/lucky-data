package com.isep.lucky_data.payload.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This is the request you should send")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EditRoleRequest {

    @ApiModelProperty(value = "True to add a new role, false to delete role", example = "True", required = true, position = 0)
    private Boolean addRole;

    @ApiModelProperty(value = "The role to edit", example = "ROLE_DATA_EXPERT", required = true, position = 2)
    private String role;

    @ApiModelProperty(value = "The email of the user", example = "example@gmail.com", required = true, position = 3)
    private String email;


    public EditRoleRequest() {
    }

    public Boolean getaddRole() {
        return addRole;
    }

    public void setaddRole(Boolean addRole) {
        this.addRole = addRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
