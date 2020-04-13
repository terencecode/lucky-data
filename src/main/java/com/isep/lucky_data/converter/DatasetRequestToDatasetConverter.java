package com.isep.lucky_data.converter;

import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.payload.request.DatasetRequest;
import com.isep.lucky_data.utils.converter.OneWayConverter;

import java.util.Date;

public class DatasetRequestToDatasetConverter extends OneWayConverter<Dataset, DatasetRequest> {
    public DatasetRequestToDatasetConverter() {
        super(datasetRequest -> {
            if (datasetRequest == null) {
                return null;
            }

            String title = datasetRequest.getTitle();
            String description = datasetRequest.getDescription();
            String source = datasetRequest.getSource();
            Date date = new Date(datasetRequest.getDate());
            Date startDate = datasetRequest.getStartDate() != null ? new Date(datasetRequest.getStartDate()) : null;
            Date endDate = datasetRequest.getEndDate() != null ? new Date(datasetRequest.getEndDate()) : null;
            Float latitude = datasetRequest.getLatitude();
            Float longitude = datasetRequest.getLongitude();
            String tag = datasetRequest.getTag();

            return new Dataset(title, description, source, new Date(), date, startDate, endDate, latitude, longitude, tag);
        });
    }
}
