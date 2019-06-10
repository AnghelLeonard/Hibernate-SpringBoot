package com.bookstore.service;

import com.bookstore.view.AuthorView;
import com.bookstore.view.AuthorViewRepository;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorViewRepository authorViewRepository;

    public BookstoreService(AuthorViewRepository authorViewRepository) {
        this.authorViewRepository = authorViewRepository;
    }

    public Iterable<AuthorView> fetchAuthors() {
        return authorViewRepository.findAll();
    }
}
