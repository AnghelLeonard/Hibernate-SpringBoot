package com.bookstore.service;

import com.bookstore.entity.Author;
import org.springframework.stereotype.Service;
import com.bookstore.repository.AuthorRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void updateAuthor() {
        Author author = authorRepository.findById(1L).orElseThrow();

        author.setSellrank(222);
    }
}
