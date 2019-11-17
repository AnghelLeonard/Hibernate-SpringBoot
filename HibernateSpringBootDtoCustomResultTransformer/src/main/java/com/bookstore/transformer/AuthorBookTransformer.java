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
            authorDto = new AuthorDto();
            authorDto.setId(((Number) os[0]).longValue());
            authorDto.setName((String) os[1]);
            authorDto.setAge((int) os[2]);
        }

        BookDto bookDto = new BookDto();
        bookDto.setId(((Number) os[3]).longValue());
        bookDto.setTitle((String) os[4]);

        authorDto.addBook(bookDto);

        authorsDtoMap.putIfAbsent(authorDto.getId(), authorDto);

        return authorDto;
    }

    @Override
    public List<AuthorDto> transformList(List list) {
        return new ArrayList<>(authorsDtoMap.values());
    }

}
