package com.bookstore.service;

import com.bookstore.entity.Book;
import org.springframework.stereotype.Service;
import com.bookstore.repository.BookRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

@Service
public class BookstoreService {

    private final BookRepository bookRepository;

    public BookstoreService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean existsBook1(Book book) {

        Example<Book> bookExample = Example.of(book);
        return bookRepository.exists(bookExample);
    }
    
    public boolean existsBook2(Book book) {

        Example<Book> bookExample = Example.of(book, 
                ExampleMatcher.matchingAll().withIgnorePaths("genre", "price"));
        return bookRepository.exists(bookExample);
    }
    
    public boolean existsBook3(Book book) {

        Example<Book> bookExample = Example.of(book, 
                ExampleMatcher.matchingAny().withIgnorePaths("genre", "price"));
        return bookRepository.exists(bookExample);
    }
}


