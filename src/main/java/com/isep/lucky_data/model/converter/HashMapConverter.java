package com.isep.lucky_data.model.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> json) {

        ObjectMapper objectMapper = new ObjectMapper();
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(json);
        } catch (final JsonProcessingException e) {
            //logger.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String stringifiedJson) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(stringifiedJson, Map.class);
        } catch (final IOException e) {
            //logger.error("JSON reading error", e);
        }

        return customerInfo;
    }

}
