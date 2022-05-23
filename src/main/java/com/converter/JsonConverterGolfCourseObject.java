package com.converter;

import com.domain.embedded.GolfCourseObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class JsonConverterGolfCourseObject implements AttributeConverter<GolfCourseObject, String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(GolfCourseObject attribute) {
        if (attribute == null) return null;

        try {
            return mapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to serialize to json field", e);
        }
    }

    @Override
    public GolfCourseObject convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        try {
            return mapper.readValue(dbData, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to serialize to json field", e);
        }
    }
}
