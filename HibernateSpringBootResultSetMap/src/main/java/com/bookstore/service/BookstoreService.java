package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void fetchIdAndAuthor() {
        
        System.out.println(authorRepository.fetchIdAuthor());
    }
    
    public void fetchGroupByGenre() {
            
        System.out.println(authorRepository.groupByGenre());
    }
}
