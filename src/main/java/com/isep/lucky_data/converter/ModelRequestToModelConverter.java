package com.isep.lucky_data.converter;

import com.isep.lucky_data.model.Dataset;
import com.isep.lucky_data.model.Model;
import com.isep.lucky_data.payload.request.DatasetRequest;
import com.isep.lucky_data.payload.request.ModelRequest;
import com.isep.lucky_data.utils.converter.OneWayConverter;

import java.util.Date;

public class ModelRequestToModelConverter extends OneWayConverter<Model, ModelRequest> {
    public ModelRequestToModelConverter() {
        super(modelRequest -> {
            if (modelRequest == null) {
                return null;
            }

            String title = modelRequest.getTitle();
            String description = modelRequest.getDescription();
            String source = modelRequest.getSource();
            String tag = modelRequest.getTag();

            return new Model(title, description, source, new Date(), tag);
        });
    }
}
