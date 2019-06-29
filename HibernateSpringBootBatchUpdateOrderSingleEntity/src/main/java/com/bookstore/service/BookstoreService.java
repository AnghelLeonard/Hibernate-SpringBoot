package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void persistAuthors() {

        List<Author> authors = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Author author = new Author();
            author.setId((long) i + 1);
            author.setName("Name_" + i);
            author.setGenre("Genre_" + i);
            author.setAge(18 + i);

            authors.add(author);
        }

        authorRepository.saveAll(authors);
    }

    @Transactional
    public void updateAuthors() {

        List<Author> authors = authorRepository.findAll();

        authors.forEach(a -> a.setAge(a.getAge() + 1));
    }
}
