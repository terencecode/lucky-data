package com.isep.lucky_data.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This is the response")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponse {

    @ApiModelProperty(value = "The first name of the user", example = "Steve", required = true, position = 0)
    private String firstName;

    @ApiModelProperty(value = "The last name of the user", example = "Jobs", required = true, position = 1)
    private String lastName;

    @ApiModelProperty(value = "The email of the user", example = "example@gmail.com", required = true, position = 2)
    private String email;

    public UserResponse(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
}
