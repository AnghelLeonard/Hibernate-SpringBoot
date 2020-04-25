package com.bookstore.transformer;

import com.bookstore.dto.AuthorDto;
import com.bookstore.dto.BookDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;

public class AuthorBookTransformer implements ResultTransformer {

    private Map<Long, AuthorDto> authorsDtoMap = new HashMap<>();

    @Override
    public Object transformTuple(Object[] os, String[] strings) {

        Long authorId = ((Number) os[0]).longValue();

        AuthorDto authorDto = authorsDtoMap.get(authorId);

        if (authorDto == null) {
            authorDto = new AuthorDto(((Number) os[0]).longValue(), (String) os[1], (int) os[2], new ArrayList<>());            
        }

        BookDto bookDto = new BookDto(((Number) os[3]).longValue(), (String) os[4]);
        
        authorDto.addBook(bookDto);

        authorsDtoMap.putIfAbsent(authorDto.id(), authorDto);

        return authorDto;
    }

    @Override
    public List<AuthorDto> transformList(List list) {
        return new ArrayList<>(authorsDtoMap.values());
    }

}
