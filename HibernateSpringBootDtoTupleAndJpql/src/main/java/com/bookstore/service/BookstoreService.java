package com.bookstore.service;

import java.util.List;
import com.bookstore.repository.AuthorRepository;
import javax.persistence.Tuple;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Tuple> fetchAuthors() {

        return authorRepository.fetchAuthors();
    }
}
