package com.isep.lucky_data.model;

import com.isep.lucky_data.model.converter.HashMapConverter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

@Entity
public class DatasetApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url", nullable = false)
    @NotBlank
    private String url;

    @Column(name = "token_name", nullable = false)
    @NotBlank
    private String tokenName;

    @Column(name = "token_value", nullable = false)
    @NotBlank
    private String tokenValue;

    @Column(name = "method", nullable = false)
    @NotBlank
    private String method;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Convert(converter = HashMapConverter.class)
    @Column(name = "body")
    private Map<String, Object> body;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Convert(converter = HashMapConverter.class)
    @Column(name = "form_data")
    private Map<String, Object> formData;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Convert(converter = HashMapConverter.class)
    @Column(name = "query_params")
    private Map<String, Object> queryParams;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Convert(converter = HashMapConverter.class)
    @Column(name = "path_params")
    private Map<String, Object> pathParams;

    /*@Column(name = "fileName")
    private String fileName;*/

    @Column(name = "content_type", nullable = false)
    @NotBlank
    private String contentType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "downloaded_at", nullable = false)
    @NotNull
    private Date downloadedAt;

    @Column(name = "validity", nullable = false)
    private Long validity;


    public DatasetApi() {
    }

    public DatasetApi(@NotBlank String url, @NotBlank String tokenName, @NotBlank String tokenValue, @NotBlank String method, Map<String, Object> body, Map<String, Object> formData, Map<String, Object> queryParams, Map<String, Object> pathParams, @NotBlank String contentType, @NotNull Date downloadedAt, @NotNull Long validity) {
        this.url = url;
        this.tokenName = tokenName;
        this.tokenValue = tokenValue;
        this.method = method;
        this.body = body;
        this.formData = formData;
        this.queryParams = queryParams;
        this.pathParams = pathParams;
        this.contentType = contentType;
        this.downloadedAt = downloadedAt;
        this.validity = validity;
    }

    public Long getId() {
        return id;
    }

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
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

    public Date getDownloadedAt() {
        return downloadedAt;
    }

    public void setDownloadedAt(Date downloadedAt) {
        this.downloadedAt = downloadedAt;
    }

    public Long getValidity() {
        return validity;
    }

    public void setValidity(Long validity) {
        this.validity = validity;
    }
}
