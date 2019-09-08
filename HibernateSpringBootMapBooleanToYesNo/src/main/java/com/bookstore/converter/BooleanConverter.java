package com.bookstore.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attr) {
        System.out.println("Convert boolean to yes/no ...");

        return attr == null ? "No" : "Yes";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        System.out.println("Convert yes/no to boolean ...");

        return !"No".equals(dbData);
    }
}
