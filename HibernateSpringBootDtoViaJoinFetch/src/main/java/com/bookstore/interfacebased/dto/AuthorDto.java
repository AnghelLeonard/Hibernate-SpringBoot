package com.bookstore.interfacebased.dto;

import java.util.List;

public interface AuthorDto {

    public String getName();
    public String getGenre();
    public List<BookDto> getBooks();
}
