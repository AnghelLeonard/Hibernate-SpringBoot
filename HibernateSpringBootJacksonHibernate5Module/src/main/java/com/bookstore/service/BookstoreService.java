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

    public Author fetchAuthorWithBooks() {
        Author author = authorRepository.findByNameWithBooks("Joana Nimar");
        
        return author;
    }
     
    public Author fetchAuthorWithoutBooks() {
        Author author = authorRepository.findByName("Joana Nimar");

        return author;
    }
}
