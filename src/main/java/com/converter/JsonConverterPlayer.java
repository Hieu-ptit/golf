package com.converter;

import com.domain.embedded.PlayerObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;

@Converter(autoApply = true)
public class JsonConverterPlayer implements AttributeConverter<List<PlayerObject>, String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<PlayerObject> attribute) {
        if (attribute == null) return null;

        try {
            return mapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to serialize to json field", e);
        }
    }

    @Override
    public List<PlayerObject> convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        try {
            return mapper.readValue(dbData, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to serialize to json field", e);
        }
    }
}
