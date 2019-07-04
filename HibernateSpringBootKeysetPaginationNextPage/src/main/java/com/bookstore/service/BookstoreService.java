package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Map<List<Author>, Boolean> fetchNextPage(long id, int limit) {
        List<Author> authors = authorRepository.fetchAll(id, limit + 1);

        if (authors.size() == (limit + 1)) {
            authors.remove(authors.size() - 1);
            return Collections.singletonMap(authors, true);
        }

        return Collections.singletonMap(authors, false);
    }
}
