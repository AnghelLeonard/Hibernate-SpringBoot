package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void persistAuthor() {
        Author author = new Author();
        
        author.setAge(43);
        author.setName("Alicia Weys");
        author.setGenre("Horror");
        author.setBestSelling(true);
        
        authorRepository.save(author);
    }
    
    public void fetchAuthor() {
        Author author = authorRepository.findByName("Alicia Weys");
        System.out.println(author);
    }
}
