package com.isep.lucky_data.converter;

import com.isep.lucky_data.exception.WrongSizeFileException;
import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.model.DatasetFile;
import com.isep.lucky_data.payload.response.DatasetDetailsResponse;
import com.isep.lucky_data.utils.converter.OneWayConverter;

import java.sql.SQLException;
import java.util.Date;

public class DatasetToDatasetDetailsResponseConverter extends OneWayConverter<DatasetDetailsResponse, Dataset> {
    public DatasetToDatasetDetailsResponseConverter() {
        super(dataset -> {
            if (dataset == null) {
                return null;
            }

            Long id = dataset.getId();
            String title = dataset.getTitle();
            String description = dataset.getDescription();
            String source = dataset.getSource();
            Long uploadedAt = dataset.getUploadedAt().getTime();
            Long date = dataset.getDate().getTime();
            Long startDate = dataset.getStartDate().getTime();
            Long endDate = dataset.getEndDate().getTime();
            Float latitude = dataset.getLatitude();
            Float longitude = dataset.getLongitude();
            String tag = dataset.getTag();
            /*DatasetFile file = dataset.getDatasetFile();
            String fileName = file.getName();
            String contentType = file.getType();
            Long size = 0L;
            try {
                size = file.getData().length();
            } catch (SQLException e) {
                throw new WrongSizeFileException(e.getMessage(), e.getCause());
            }*/
            Long downloads = dataset.getDownloads();

            return new DatasetDetailsResponse(id, title, description, source, uploadedAt, date, startDate, endDate, latitude, longitude, tag, downloads);
        });
    }
}
