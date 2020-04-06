package com.isep.lucky_data.converter;

import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.payload.request.DatasetRequest;
import com.isep.lucky_data.payload.response.DatasetResponse;
import com.isep.lucky_data.utils.converter.OneWayConverter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class DatasetToDatasetResponseConverter extends OneWayConverter<DatasetResponse, Dataset> {
    public DatasetToDatasetResponseConverter() {
        super(dataset -> {
            if (dataset == null) {
                return null;
            }

            String title = dataset.getTitle();
            String description = dataset.getDescription();
            String source = dataset.getSource();
            Float latitude = dataset.getLatitude();
            Float longitude = dataset.getLongitude();
            Long startDate = dataset.getStartDate();
            Long endDate = dataset.getEndDate();
            String tag = dataset.getTag();

            return new DatasetResponse(title, description, source, latitude, longitude, startDate, endDate, tag);
        });
    }
}
