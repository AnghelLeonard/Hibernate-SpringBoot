package com.bookstore.service;

import com.bookstore.repository.BookRepository;
import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {
          
    private final BookRepository bookRepository;
    
    public BookstoreService(BookRepository bookRepository) {        
        this.bookRepository = bookRepository;
    }
    
    public void fetchBooks() {
        List<Book> books = bookRepository.findAll();
        
        books.forEach(System.out::println);
    }
}
