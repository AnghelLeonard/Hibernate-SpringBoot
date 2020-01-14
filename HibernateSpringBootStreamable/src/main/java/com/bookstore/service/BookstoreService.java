package com.bookstore.service;

import com.bookstore.dto.AuthorName;
import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    public void fetchAuthorsAsStreamable() {

        Streamable<Author> authors = authorRepository.findByGenre("Anthology");
        authors.forEach(System.out::println);
    }

    public void fetchAuthorsDtoAsStreamable() {

        Streamable<AuthorName> authors = authorRepository.findBy();
        authors.forEach(a -> System.out.println(a.getName()));
    }

    // Caution: Don't do this! Fetching all columns just to drop a part of them
    public void fetchAuthorsNames() {

        Streamable<String> authors = authorRepository.findByGenre("Anthology")
                .map(Author::getName);

        authors.forEach(System.out::println);
    }

    // Caution: Don't do this! Fetching all rows just to throw away a part of it
    public void fetchAuthorsOlderThan40() {

        Streamable<Author> authors = authorRepository.findByGenre("Anthology")
                .filter(a -> a.getAge() > 40);

        authors.forEach(System.out::println);
    }

    // Caution: Don't do this! It triggers two SELECT statements!
    @Transactional(readOnly = true)
    public void fetchAuthorsByGenreAndAge() {

        Streamable<Author> authors = authorRepository.findByGenre("Anthology")
                .and(authorRepository.findByAgeGreaterThan(40));

        authors.forEach(System.out::println);
    }
}
