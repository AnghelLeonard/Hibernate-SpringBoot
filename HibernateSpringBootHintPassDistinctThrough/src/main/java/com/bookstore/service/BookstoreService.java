package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void fetchWithDuplicates() {
        System.out.println("\nFetching authors with duplicates ...");

        List<Author> authors = authorRepository.fetchWithDuplicates();

        authors.forEach(a -> {
            System.out.println("Id: " + a.getId() + ": Name: " + a.getName());
        });
    }

    public void fetchWithoutHint() {
        System.out.println("\nFetching authors without HINT_PASS_DISTINCT_THROUGH hint ...");

        List<Author> authors = authorRepository.fetchWithoutHint();

        authors.forEach(a -> {
            System.out.println("Id: " + a.getId() + ": Name: " + a.getName());
        });
    }

    public void fetchWithHint() {
        System.out.println("\nFetching authors with HINT_PASS_DISTINCT_THROUGH hint ...");

        List<Author> authors = authorRepository.fetchWithHint();

        authors.forEach(a -> {
            System.out.println("Id: " + a.getId() + ": Name: " + a.getName());
        });
    }
}
