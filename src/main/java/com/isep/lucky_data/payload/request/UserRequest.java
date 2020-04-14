package com.isep.lucky_data.payload.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This is the request you should send")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserRequest {

    @ApiModelProperty(value = "The first name of the user", example = "Steve", required = true, position = 0)
    private String firstName;

    @ApiModelProperty(value = "The last name of the user", example = "Jobs", required = true, position = 1)
    private String lastName;

    @ApiModelProperty(value = "The email of the user", example = "example@gmail.com", required = true, position = 2)
    private String email;

    @ApiModelProperty(value = "The password of the user", example = "MySuperS3cretPassword!", required = true, position = 3)
    private String password;

    public UserRequest() {
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
}
