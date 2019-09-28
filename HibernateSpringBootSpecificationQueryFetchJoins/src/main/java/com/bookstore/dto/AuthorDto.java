package com.bookstore.dto;

import java.util.List;

public interface AuthorDto {

    public Long getId();
    public String getName();
    public String getGenre();
    public int getAge();
    public List<BookDto> getBooks();
}
