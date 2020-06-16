package com.isep.lucky_data.converter;

import com.isep.lucky_data.model.Model;
import com.isep.lucky_data.payload.response.ModelDetailsResponse;
import com.isep.lucky_data.utils.converter.OneWayConverter;

public class ModelToModelDetailsResponseConverter extends OneWayConverter<ModelDetailsResponse, Model> {
    public ModelToModelDetailsResponseConverter() {
        super(model -> {
            if (model == null) {
                return null;
            }

            Long id = model.getId();
            String title = model.getTitle();
            String description = model.getDescription();
            String source = model.getSource();
            Long uploadedAt = model.getUploadedAt().getTime();

            String tag = model.getTag() != null ? model.getTag() : null;
            Long downloads = model.getDownloads();

            return new ModelDetailsResponse(id, title, description, source, uploadedAt, tag, downloads);
        });
    }
}
