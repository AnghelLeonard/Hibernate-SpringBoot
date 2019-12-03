package com.bookstore.transform.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class AuthorTransformer {

    public List<AuthorDto> transform(List<Object[]> rs) {

        final Map<Long, AuthorDto> authorsDtoMap = new HashMap<>();

        for (Object[] o : rs) {
            
            Long authorId = ((Number) o[0]).longValue();

            AuthorDto authorDto = authorsDtoMap.get(authorId);
            if (authorDto == null) {
                authorDto = new AuthorDto();
                authorDto.setId(((Number) o[0]).longValue());
                authorDto.setName((String) o[1]);
                authorDto.setGenre((String) o[2]);
            }

            BookDto bookDto = new BookDto();
            bookDto.setId(((Number) o[3]).longValue());
            bookDto.setTitle((String) o[4]);

            authorDto.addBook(bookDto);
            authorsDtoMap.putIfAbsent(authorDto.getId(), authorDto);
        }

        return new ArrayList<>(authorsDtoMap.values());
    }
}
