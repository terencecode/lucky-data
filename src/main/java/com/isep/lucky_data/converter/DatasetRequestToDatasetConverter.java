package com.isep.lucky_data.converter;

import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.payload.request.DatasetRequest;
import com.isep.lucky_data.utils.converter.OneWayConverter;

public class DatasetRequestToDatasetConverter extends OneWayConverter<Dataset, DatasetRequest> {
    public DatasetRequestToDatasetConverter() {
        super(datasetRequest -> {
            if (datasetRequest == null) {
                return null;
            }

            String title = datasetRequest.getTitle();
            String description = datasetRequest.getDescription();
            String source = datasetRequest.getSource();
            Float latitude = datasetRequest.getLatitude();
            Float longitude = datasetRequest.getLongitude();
            Long startDate = datasetRequest.getStartDate();
            Long endDate = datasetRequest.getEndDate();
            String tag = datasetRequest.getTag();

            return new Dataset(title, description, source, latitude, longitude, startDate, endDate, tag);
        });
    }
}
