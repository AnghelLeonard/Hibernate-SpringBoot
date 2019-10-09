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

    @Transactional(timeout = 10)
    public void newAuthor() {
        
        Author author = new Author();
        author.setAge(23);
        author.setGenre("Anthology");
        author.setName("Mark Janel");
        authorRepository.saveAndFlush(author);        
              
        authorRepository.sleepQuery();
        
        System.out.println("The end!");
    }
}
