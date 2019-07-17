package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
import java.time.YearMonth;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final BookRepository bookRepository;

    public BookstoreService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void newBook() {

        Book book = new Book();

        book.setIsbn("001");
        book.setTitle("Young Boy");
        book.setReleaseDate(YearMonth.now());

        bookRepository.save(book);
    }
    
    public void displayBook() {    
        Book book = bookRepository.findByTitle("Young Boy");
        
        System.out.println(book);
    }    
}
