package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
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

    public List<Author> fetchAuthorWithBooks() {

        return authorRepository.fetchAuthorWithBooks();
    }

    public List<Book> fetchBookWithAuthor() {

        return bookRepository.fetchBookWithAuthor();
    }
}
