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

    public void fetchAuthorsByGenreAndAgeGreaterThan() {

        Streamable<Author> authors = authorRepository.findByGenreAndAgeGreaterThan("Anthology", 40);
        authors.forEach(System.out::println);
    }
    
    public void fetchAuthorsByGenreOrAgeGreaterThan() {

        Streamable<Author> authors = authorRepository.findByGenreOrAgeGreaterThan("Anthology", 40);
        authors.forEach(System.out::println);
    }

    // Caution: Don't do this! Fetch all columns just to drop a part of them
    public void fetchAuthorsNames1() {

        Streamable<String> authors = authorRepository.findByGenre("Anthology")
                .map(Author::getName);

        authors.forEach(System.out::println);
    }
    
    // Do this
    public void fetchAuthorsNames2() {

        Streamable<AuthorName> authors = authorRepository.queryByGenre("Anthology");
        authors.forEach(a -> System.out.println(a.getName()));
    }

    // Caution: Don't do this! Fetch more rows than needed just to throw away a part of it
    public void fetchAuthorsOlderThanAge1() {

        Streamable<Author> authors = authorRepository.findByGenre("Anthology")
                .filter(a -> a.getAge() > 40);

        authors.forEach(System.out::println);
    }
    
    // Do this! Fetch more rows than needed just to throw away a part of it
    public void fetchAuthorsOlderThanAge2() {

        Streamable<Author> authors = authorRepository.findByGenreAndAgeGreaterThan("Anthology", 40);
        authors.forEach(System.out::println);
    }

    // Caution: It triggers two SELECT statements and the
    // final result is the concatenation of the these two results sets!
    @Transactional(readOnly = true)
    public void fetchAuthorsByGenreConcatAge() {

        Streamable<Author> authors = authorRepository.findByGenre("Anthology")
                .and(authorRepository.findByAgeGreaterThan(40));

        authors.forEach(System.out::println);
    }
}
