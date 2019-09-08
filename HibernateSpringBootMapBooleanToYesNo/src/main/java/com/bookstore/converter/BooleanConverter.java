package com.bookstore.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attr) {
        if (attr != null) {
            System.out.println("Convert boolean to yes/no ...");
            if (attr) {
                return "Yes";
            } else {
                return "No";
            }

        }
        return null;
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            System.out.println("Convert yes/no to boolean ...");
            return dbData.equals("Yes");
        }
        return null;
    }

}
