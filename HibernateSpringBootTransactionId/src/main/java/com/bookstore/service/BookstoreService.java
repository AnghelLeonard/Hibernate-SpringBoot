package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void displayTransactionId() {
        
        // we need an SQL statement to generate a transaction id
        authorRepository.findFirstByGenre("Anthology"); 
        
        Object transactionId = authorRepository.getTransactionId();
        System.out.println("Transaction id: " + transactionId);
    }
}
