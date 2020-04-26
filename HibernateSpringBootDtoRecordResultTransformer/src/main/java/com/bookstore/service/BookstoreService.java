package com.bookstore.service;

import com.bookstore.dao.Dao;
import com.bookstore.dto.AuthorDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public record BookstoreService(Dao dao) {   

    public List<AuthorDto> fetchAuthorWithBook() {

        List<AuthorDto> authors = dao.fetchAuthorWithBook();        

        return authors;
    }
}
