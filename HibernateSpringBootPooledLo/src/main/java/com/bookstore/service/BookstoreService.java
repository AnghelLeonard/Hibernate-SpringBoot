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
    public void save3Authors() {

        for (int i = 1; i <= 3; i++) {
            Author author = new Author();
            author.setName("Author_" + i);

            authorRepository.save(author); // uses ids: 1, 2 and 3
        }
    }

    @Transactional
    public void saveAuthorNative() {        
        authorRepository.saveNative("Author_" + 4); // successfully inserted at id 101
    }
}
