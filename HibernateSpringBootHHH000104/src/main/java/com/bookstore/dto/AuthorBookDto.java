package com.bookstore.dto;

import com.bookstore.entity.Author;

public interface AuthorBookDto {

    public Author getAuthor();

    public String getTitle();

    public String getIsbn();
}
