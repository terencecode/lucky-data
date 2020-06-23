package com.isep.lucky_data.payload.request;

import io.swagger.annotations.ApiModelProperty;

public class DatasetAPIFileRefreshRequest {

    @ApiModelProperty(value = "The value of the token", example = "", required = false, position = 0)
    private String token;

    public String getToken() {
        return token;
    }
}
