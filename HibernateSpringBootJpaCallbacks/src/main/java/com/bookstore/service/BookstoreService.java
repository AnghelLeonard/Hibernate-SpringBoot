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
    public void newAuthor() {

        Author author = new Author();
        author.setName("Joana Nimar");
        author.setAge(34);
        author.setGenre("History");

        // this will call @Pre/PostPersist callback
        authorRepository.save(author);
    }

    @Transactional
    public void selectUpdateDeleteAuthor() {

        // this will call @PostLoad (the user is in persistent context)
        Author author = authorRepository.findById(1L).orElseThrow();

        // force update, so @Pre/PostUpdate will be called
        author.setAge(35);
        authorRepository.saveAndFlush(author);

        // this will call @Pre/PostRemove callback
        authorRepository.delete(author);
    }
}
