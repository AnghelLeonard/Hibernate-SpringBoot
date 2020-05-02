package com.bookstore.service;

import com.bookstore.jdbcTemplate.dto.AuthorDto;
import com.bookstore.jdbcTemplate.dto.AuthorExtractor;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorExtractor authorExtractor;

    public BookstoreService(AuthorExtractor authorExtractor) {
        this.authorExtractor = authorExtractor;
    }

     public List<AuthorDto> fetchAuthorsWithBooksViaJdbcTemplateToDto() {
         
         return authorExtractor.extract();
     }
}
