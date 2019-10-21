package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookMultipleIdsRepository;
import com.bookstore.repository.BookRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final BookMultipleIdsRepository bookMultipleIdsRepository;
    private final BookRepository bookRepository;

    public BookstoreService(BookMultipleIdsRepository bookMultipleIdsRepository,
            BookRepository bookRepository) {
        this.bookMultipleIdsRepository = bookMultipleIdsRepository;
        this.bookRepository = bookRepository;
    }
    
    public void fetchByMultipleIdsIn() {
        List<Book> books = bookRepository.fetchByMultipleIds(List.of(1L, 2L, 5L));
        
        System.out.println(books);                
    }
    
    public void fetchByMultipleIds() {
        List<Book> books = bookMultipleIdsRepository
                .fetchByMultipleIds(List.of(1L, 2L, 5L));
        
        System.out.println(books);                
    }
    
    public void fetchInBatchesByMultipleIds() {
        List<Book> books = bookMultipleIdsRepository
                .fetchInBatchesByMultipleIds(List.of(1L, 2L, 5L), 2);
        
        System.out.println(books);                
    }
    
    @Transactional(readOnly = true)
    public void fetchBySessionCheckMultipleIds() {
        List<Book> books1 = bookMultipleIdsRepository
                .fetchByMultipleIds(List.of(1L, 2L, 5L));
        
        System.out.println(books1);
        
        // loaded from Persistence Context
        List<Book> books2 = bookMultipleIdsRepository
                .fetchBySessionCheckMultipleIds(List.of(1L, 2L, 5L));        
        
        System.out.println(books2);
    }
      
    public void fetchInBatchesBySessionCheckMultipleIds() {
        List<Book> books = bookMultipleIdsRepository
                .fetchInBatchesBySessionCheckMultipleIds(List.of(1L, 2L, 3L), 2);
        
        System.out.println(books);            
    }
}
