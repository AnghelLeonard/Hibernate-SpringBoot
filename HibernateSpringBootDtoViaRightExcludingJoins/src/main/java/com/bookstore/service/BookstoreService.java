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

    // Fetch books and authors excluding books that have registered authors (JPQL)    
    public List<AuthorNameBookTitle> fetchBooksAndAuthorsJpql() {
        return bookRepository.findBooksAndAuthorsJpql();
    }

    // Fetch books and authors excluding books that have registered authors (SQL)    
    public List<AuthorNameBookTitle> fetchBooksAndAuthorsSql() {
        return bookRepository.findBooksAndAuthorsSql();
    }

    // Fetch authors and books excluding authors that have registered books (JPQL)    
    public List<AuthorNameBookTitle> fetchAuthorsAndBooksJpql() {
        return authorRepository.findAuthorsAndBooksJpql();
    }

    // Fetch authors and books excluding authors that have registered books (SQL)    
    public List<AuthorNameBookTitle> fetchAuthorsAndBooksSql() {
        return authorRepository.findAuthorsAndBooksSql();
    }
}
