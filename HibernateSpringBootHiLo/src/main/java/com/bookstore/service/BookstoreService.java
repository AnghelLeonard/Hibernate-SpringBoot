package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void batch1000Authors() {

        List<Author> authors = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {
            Author author = new Author();
            author.setName("Author_" + i);

            authors.add(author);
        }

        authorRepository.saveAll(authors);
    }
}
