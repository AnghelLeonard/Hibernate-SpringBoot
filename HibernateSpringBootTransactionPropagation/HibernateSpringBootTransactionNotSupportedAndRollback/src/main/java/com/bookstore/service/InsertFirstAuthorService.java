package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InsertFirstAuthorService {

    private final AuthorRepository authorRepository;
    private final InsertSecondAuthorService insertSecondAuthorService;

    public InsertFirstAuthorService(AuthorRepository authorRepository,
            InsertSecondAuthorService insertSecondAuthorService) {
        this.authorRepository = authorRepository;
        this.insertSecondAuthorService = insertSecondAuthorService;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertFirstAuthor() {

        Author author = new Author();
        author.setName("Joana Nimar");

        authorRepository.save(author);

        insertSecondAuthorService.insertSecondAuthor();
    }
}
