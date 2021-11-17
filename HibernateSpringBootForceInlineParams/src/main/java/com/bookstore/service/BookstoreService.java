package com.bookstore.service;

import com.bookstore.dao.Dao;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final Dao dao;

    public BookstoreService(Dao dao) {
        this.dao = dao;
    }

    public List<Author> fetchAuthors() {

        return dao.fetchAuthors();
    }
}
