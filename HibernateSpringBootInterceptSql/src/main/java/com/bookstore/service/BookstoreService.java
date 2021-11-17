package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository,
            BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author fetchAuthorWithBooksByName() {

        return authorRepository.fetchAuthorWithBooksByName("Joana Nimar");
    }

    public Book fetchBookWithAuthorByIsbn() {

        return bookRepository.fetchBookWithAuthorByIsbn("002-JN");
    }
}
