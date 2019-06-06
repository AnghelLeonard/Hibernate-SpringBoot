package com.bookstore.service;

import java.util.List;
import com.bookstore.projection.AuthorNameAge;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorNameAge> fetchFirst2ByBirthplace() {

        return authorRepository.findFirst2ByGenre("Anthology");
    }
}
