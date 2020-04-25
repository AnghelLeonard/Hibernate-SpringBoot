package com.bookstore.dao;

import java.util.List;

import com.bookstore.dto.AuthorDto;

public interface AuthorDao {
    
    public List<AuthorDto> fetchAuthorWithBook();
}
