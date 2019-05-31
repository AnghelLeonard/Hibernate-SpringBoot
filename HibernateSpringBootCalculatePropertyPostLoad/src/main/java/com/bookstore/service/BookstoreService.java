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
    
    public void fetchBooks() {
        List<Book> books = bookRepository.findAll();
        
        books.forEach(System.out::println);
    }
}
