package com.isep.lucky_data.payload.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This is the response returned")
public class JwtAuthenticationResponse {

    @ApiModelProperty(value = "The accessToken", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNTg2ODA0ODU1LCJleHAiOjE1ODc0MDk2NTV9.hqUvc5rNPUieWRODdyUvshnPDJXo8sue8SCj3gDVNqw18nSQO7o9bco2Qx9V5FQo6eoW_Y1vcpIe6VPluljRBQ", required = true, position = 0)
    private String accessToken;

    @ApiModelProperty(value = "The type of the token", example = "Bearer", required = true, position = 1)
    private String tokenType = "Bearer";

    @ApiModelProperty(value = "The date of expiation of the token (in timestamp milliseconds)", example = "1586804945883", required = true, position = 2)
    private Long expiresAt;

    public JwtAuthenticationResponse(String accessToken, Long expiresAt) {
        this.accessToken = accessToken;
        this.expiresAt = expiresAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }
}
