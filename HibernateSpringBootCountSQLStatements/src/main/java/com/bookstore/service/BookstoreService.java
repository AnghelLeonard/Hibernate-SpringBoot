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

    public void authorOperationsWithoutTransactional() {

        Author author = authorRepository.findById(1L).orElseThrow();
        author.setGenre("History");

        authorRepository.save(author);
    }

    @Transactional
    public void authorOperationsWithTransactional() {
        Author author = authorRepository.findById(1L).orElseThrow();
        author.setGenre("Anthology");
    }

}
