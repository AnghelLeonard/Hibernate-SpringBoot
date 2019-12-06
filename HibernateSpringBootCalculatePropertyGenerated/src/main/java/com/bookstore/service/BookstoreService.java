package com.bookstore.service;

import com.bookstore.repository.BookRepository;
import java.util.logging.Logger;
import com.bookstore.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        System.out.println("Discounted price after insert: " + book.getDiscounted());
    }

    @Transactional
    public void updateBook() {
        Book book = bookRepository.findByTitle("Ancient History");
        book.setPrice(9.99);
        
        bookRepository.flush();

        System.out.println("Discounted price after update: " + book.getDiscounted());
    }
}