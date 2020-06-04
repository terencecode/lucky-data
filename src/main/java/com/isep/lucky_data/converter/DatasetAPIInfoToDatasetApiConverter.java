package com.isep.lucky_data.converter;

import com.isep.lucky_data.model.DatasetApi;
import com.isep.lucky_data.payload.request.DatasetAPIInfo;
import com.isep.lucky_data.utils.converter.OneWayConverter;

import java.util.Date;
import java.util.Map;

public class DatasetAPIInfoToDatasetApiConverter extends OneWayConverter<DatasetApi, DatasetAPIInfo> {
    public DatasetAPIInfoToDatasetApiConverter() {
        super(datasetAPIInfo -> {
            if (datasetAPIInfo == null) {
                return null;
            }
            String url = datasetAPIInfo.getUrl();
            String tokenName = datasetAPIInfo.getTokenName();
            String tokenValue = datasetAPIInfo.getTokenValue();
            String method = datasetAPIInfo.getMethod().toString();
            Map<String, Object> body = datasetAPIInfo.getBody();
            Map<String, Object> formData = datasetAPIInfo.getFormData();
            Map<String, Object> queryParams = datasetAPIInfo.getQueryParams();
            Map<String, Object> pathParams = datasetAPIInfo.getPathParams();
            String contentType = datasetAPIInfo.getContentType();
            Long validity = datasetAPIInfo.getValidity();
            return new DatasetApi(url, tokenName, tokenValue, method, body, formData, queryParams, pathParams, contentType, new Date(), validity);
        });
    }
}
