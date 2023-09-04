package com.example.course.enums.conveters;

import com.example.course.enums.Category;
import com.example.course.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status category) {
        if(category == null){
            return null;
        }
        return category.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String s) {
        if(s == null){
            return null;
        }
        return Stream.of(Status.values())
                .filter(c -> c.getValue().equals(s))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
