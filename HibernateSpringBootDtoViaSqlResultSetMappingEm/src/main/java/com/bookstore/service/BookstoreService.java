package com.bookstore.service;

import com.bookstore.dao.AuthorDaoImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import com.bookstore.dto.AuthorDto;

@Service
public class BookstoreService {

    private final AuthorDaoImpl dao;

    public BookstoreService(AuthorDaoImpl dao) {
        this.dao = dao;
    }

    public List<AuthorDto> fetchAuthorsNamesAndAges() {

        return dao.fetchNameAndAge();
    }
}
