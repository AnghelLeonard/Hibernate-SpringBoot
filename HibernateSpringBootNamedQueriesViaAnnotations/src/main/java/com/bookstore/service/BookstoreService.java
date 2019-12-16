package com.bookstore.service;

import java.util.List;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import com.bookstore.entity.Author;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> fetchAuthorsGtAgeDescName() {

        return authorRepository.fetchGtAgeDescName(30);
    }

    public Author fetchAuthorByNameAndAge() {

        return authorRepository.fetchByNameAndAge("Joana Nimar", 34);
    }

    public List<Author> fetchAuthorsGtAgeDescNameNative() {

        return authorRepository.fetchGtAgeDescNameNative(30);
    }

    public Author fetchAuthorByNameAndAgeNative() {

        return authorRepository.fetchByNameAndAgeNative("Joana Nimar", 34);
    }
}
