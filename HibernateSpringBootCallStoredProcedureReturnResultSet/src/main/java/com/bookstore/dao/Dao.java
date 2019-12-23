package com.bookstore.dao;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import java.util.List;

public interface Dao {

    public List<Author> fetchByGenre1(String genre);
    public List<Author> fetchByGenre2(String genre);
    public List<AuthorDto> fetchByGenre3(String genre);
    public List<AuthorDto> fetchByGenre4(String genre);
}
