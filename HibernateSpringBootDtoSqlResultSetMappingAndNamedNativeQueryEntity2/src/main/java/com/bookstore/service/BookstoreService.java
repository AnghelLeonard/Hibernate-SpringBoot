package com.bookstore.service;

import com.bookstore.entity.Author;
import java.util.List;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> fetchAuthors() {

        return authorRepository.fetchAll();
    }

    public List<Object[]> fetchAuthorWithBook() {
        
        return authorRepository.fetchWithBook();
    }
}
