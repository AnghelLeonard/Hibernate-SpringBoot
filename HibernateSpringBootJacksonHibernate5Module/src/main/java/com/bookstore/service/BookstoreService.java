package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {
   
    private final AuthorRepository authorRepository;    

    public BookstoreService(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;        
    }

    public Author fetchAuthorByName(String name) {
        return authorRepository.findByName(name);
    }
}
