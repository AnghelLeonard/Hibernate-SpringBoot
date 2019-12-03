package com.bookstore.spring.dto;

import java.util.List;

public interface AuthorDto {

    public String getName();
    public String getGenre();
    public List<BookDto> getBooks();

    interface BookDto {
        public String getTitle();
    }
}
