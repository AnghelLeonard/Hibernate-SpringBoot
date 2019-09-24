package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> fetchNextPage(int start, int end) {

        List<Author> authors = authorRepository.fetchPage(start, end);

        return authors;
    }
}
