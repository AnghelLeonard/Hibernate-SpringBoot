package com.bookstore.dto;

import java.util.List;

public interface AuthorDto {

    public String getName();
    public String getGenre();
    public List<BookDto> getBooks();
    
    static interface BookDto {
        public String getTitle();
    }     
}
