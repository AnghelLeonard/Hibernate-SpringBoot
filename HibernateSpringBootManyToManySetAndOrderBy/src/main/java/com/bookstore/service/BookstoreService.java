package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.repository.BookRepository;
import java.util.Set;

@Service
public class BookstoreService {

    private final BookRepository bookRepository;

    public BookstoreService(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    @Transactional
    public void fetchAuthorsOfBook() {
        Book book = bookRepository.findById(1L).orElseThrow();
        Set<Author> authorsSet = book.getAuthors();

        System.out.println("Authors ordered descending by name:\n" + authorsSet);
    }
}
