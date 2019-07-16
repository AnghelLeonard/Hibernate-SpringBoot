package com.bookstore.service;

import com.bookstore.dao.Dao;
import java.time.Instant;
import org.springframework.stereotype.Service;
import com.bookstore.repository.BookRepository;

@Service
public class BookstoreService {

    private final Dao dao;
    private final BookRepository bookRepository;

    public BookstoreService(Dao dao, BookRepository bookRepository) {
        this.dao = dao;
        this.bookRepository = bookRepository;
    }

    public String titleAndPrice() {
        return bookRepository.fetchTitleAndPrice("$", Instant.now());
        // or, via EntityManager from Dao class
        //return dao.fetchNameAndAmount("$", Instant.now());
    }
}
