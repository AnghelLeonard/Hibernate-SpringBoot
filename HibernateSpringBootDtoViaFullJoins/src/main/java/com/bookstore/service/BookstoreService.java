package com.bookstore.service;

import com.bookstore.projection.AuthorNameBookTitle;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import java.util.List;
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

    // Books and authors (JPQL)
    public List<AuthorNameBookTitle> fetchBooksAndAuthorsJpql() {
        return bookRepository.findBooksAndAuthorsJpql();
    }

    // Books and authors (SQL)
    public List<AuthorNameBookTitle> fetchBooksAndAuthorsSql() {
        return bookRepository.findBooksAndAuthorsSql();
    }

    // Authors and books (JPQL)
    public List<AuthorNameBookTitle> fetchAuthorsAndBooksJpql() {
        return authorRepository.findAuthorsAndBooksJpql();
    }

    // Authors and books (SQL)
    public List<AuthorNameBookTitle> fetchAuthorsAndBooksSql() {
        return authorRepository.findAuthorsAndBooksSql();
    }
}
