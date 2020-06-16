package com.isep.lucky_data.converter;

import com.isep.lucky_data.model.Model;
import com.isep.lucky_data.payload.response.ModelResponse;
import com.isep.lucky_data.utils.converter.OneWayConverter;

public class ModelToModelResponseConverter extends OneWayConverter<ModelResponse, Model> {
    public ModelToModelResponseConverter() {
        super(dataset -> {
            if (dataset == null) {
                return null;
            }

            Long id = dataset.getId();
            String title = dataset.getTitle();
            String description = dataset.getDescription();
            String source = dataset.getSource();
            String tag = dataset.getTag();
            Long uploadedAt = dataset.getUploadedAt().getTime();
            Long downloads = dataset.getDownloads();

            return new ModelResponse(id, title, description, source, tag, uploadedAt, downloads);
        });
    }
}
