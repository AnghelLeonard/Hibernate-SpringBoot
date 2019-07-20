package com.bookstore.service;

import com.bookstore.repository.BookRepository;
import java.util.logging.Logger;
import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private static final Logger logger
            = Logger.getLogger(BookstoreService.class.getName());

    private final BookRepository bookRepository;

    public BookstoreService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void insertBook() {
        Book book = new Book();

        book.setTitle("Ancient History");
        book.setIsbn("001-AH");
        book.setPrice(13.99);

        bookRepository.save(book);
    }

    public Book fetchBook() {
        return bookRepository.findByTitle("Ancient History");
    }
}
