package com.isep.lucky_data.payload.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "Request", description = "This is the request you should send")
public class LoginRequest {

    @NotBlank
    @ApiModelProperty(value = "The email of the user", example = "example@gmail.com", required = true, position = 0)
    private String email;

    @NotBlank
    @ApiModelProperty(value = "The password of the user", example = "MySuperS3cretPassword!", required = true, position = 1)
    private String password;

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
