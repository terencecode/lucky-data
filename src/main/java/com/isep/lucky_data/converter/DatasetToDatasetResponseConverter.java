package com.isep.lucky_data.converter;

import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.payload.response.DatasetResponse;
import com.isep.lucky_data.utils.converter.OneWayConverter;

public class DatasetToDatasetResponseConverter extends OneWayConverter<DatasetResponse, Dataset> {
    public DatasetToDatasetResponseConverter() {
        super(dataset -> {
            if (dataset == null) {
                return null;
            }

            Long id = dataset.getId();
            String title = dataset.getTitle();
            String description = dataset.getDescription();
            String source = dataset.getSource();
            String tag = dataset.getTag();
            Long date = dataset.getDate().getTime();
            Long downloads = dataset.getDownloads();

            return new DatasetResponse(id, title, description, source, tag, date, downloads, dataset.getDatasetApi() != null);
        });
    }
}
