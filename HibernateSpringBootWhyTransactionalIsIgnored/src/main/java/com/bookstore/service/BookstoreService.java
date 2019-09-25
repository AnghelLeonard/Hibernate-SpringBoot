package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private static final Logger log = Logger.getLogger(BookstoreService.class.getName());   
    
    private final AuthorRepository authorRepository;
    private final HelperService helperService;

    public BookstoreService(AuthorRepository authorRepository, HelperService helperService) {
        this.authorRepository = authorRepository;
        this.helperService = helperService;
    }

    public void mainAuthor() {
        Author author = new Author();
        // persistAuthor(author);
        helperService.persistAuthor(author);
        notifyAuthor(author);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private long persistAuthor(Author author) {
        authorRepository.save(author);
        return authorRepository.count();
    }

    public void notifyAuthor(Author author) {
        log.info(() -> "Saving author: " + author);
    }
}
