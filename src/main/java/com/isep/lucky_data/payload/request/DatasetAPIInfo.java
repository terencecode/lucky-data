package com.isep.lucky_data.payload.request;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpMethod;

import javax.validation.constraints.NotBlank;
import java.util.Map;

public class DatasetAPIInfo {

    @NotBlank
    @ApiModelProperty(value = "The url of the API", example = "")
    private String url;

    @NotBlank
    @ApiModelProperty(value = "The name of the token", example = "Bearer")
    private String tokenName;

    @NotBlank
    @ApiModelProperty(value = "The value of the token", example = "")
    private String tokenValue;

    @NotBlank
    @ApiModelProperty(value = "The http method", example = "GET")
    private HttpMethod method;

    @ApiModelProperty(value = "The body of the request", example = "{q: 'query'}")
    private Map<String, Object> body;

    @ApiModelProperty(value = "The formData of the request", example = "{q: 'query'}")
    private Map<String, Object> formData;

    @ApiModelProperty(value = "The queryParams of the request", example = "{q: 'query'}")
    private Map<String, Object> queryParams;

    @ApiModelProperty(value = "The pathParams of the request", example = "{q: 'query'}")
    private Map<String, Object> pathParams;

    /*@ApiModelProperty(value = "The optional file name", example = "my_super_file")
    private String fileName;*/

    @ApiModelProperty(value = "The expected response Content Type", example = "")
    private String contentType;

    @ApiModelProperty(value = "The validity period in milliseconds", example = "")
    private Long validity;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public Map<String, Object> getFormData() {
        return formData;
    }

    public void setFormData(Map<String, Object> formData) {
        this.formData = formData;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    public Map<String, Object> getPathParams() {
        return pathParams;
    }

    public void setPathParams(Map<String, Object> pathParams) {
        this.pathParams = pathParams;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getValidity() {
        return validity;
    }

    public void setValidity(Long validity) {
        this.validity = validity;
    }
}
