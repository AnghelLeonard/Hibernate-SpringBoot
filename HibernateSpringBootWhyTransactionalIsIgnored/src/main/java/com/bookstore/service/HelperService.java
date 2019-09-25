package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HelperService {

    private final AuthorRepository authorRepository;

    public HelperService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long persistAuthor(Author author) {
        authorRepository.save(author);
        return authorRepository.count();
    }
}
