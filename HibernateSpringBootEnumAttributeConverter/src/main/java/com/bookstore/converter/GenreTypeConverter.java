package com.bookstore.converter;

import com.bookstore.enums.GenreType;
import static com.bookstore.enums.GenreType.ANTHOLOGY;
import static com.bookstore.enums.GenreType.HISTORY;
import static com.bookstore.enums.GenreType.HORROR;
import javax.persistence.AttributeConverter;

public class GenreTypeConverter implements AttributeConverter<GenreType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(GenreType attr) {
        
        if (attr == null) {
            return null;
        }

        switch (attr) {
            case HORROR:
                return 1;
            case ANTHOLOGY:
                return 2;
            case HISTORY:
                return 3;
            default:
                throw new IllegalArgumentException("The " + attr + " not supported.");
        }
    }

    @Override
    public GenreType convertToEntityAttribute(Integer dbData) {
        
        if (dbData == null) {
            return null;
        }

        switch (dbData) {
            case 1:
                return HORROR;
            case 2:
                return ANTHOLOGY;
            case 3:
                return HISTORY;
            default:
                throw new IllegalArgumentException("The " + dbData + " not supported.");
        }
    }
}
