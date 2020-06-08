package com.bookstore.service;

import com.bookstore.dto.AuthorGenreDto;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.entity.Author;
import java.util.List;
import com.bookstore.dto.AuthorNameEmailDto;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {

        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public void fetchAuthorByNameAsEntityJpql() {
        Author author = authorRepository.findByName("Joana Nimar", Author.class);

        System.out.println(author);
    }

    @Transactional(readOnly = true)
    public void fetchAuthorByNameAsDtoNameEmailJpql() {
        AuthorNameEmailDto author = authorRepository.findByName("Joana Nimar", AuthorNameEmailDto.class);

        System.out.println(author.getEmail() + ", " + author.getName());
    }

    @Transactional(readOnly = true)
    public void fetchAuthorByNameAsDtoGenreJpql() {
        AuthorGenreDto author = authorRepository.findByName("Joana Nimar", AuthorGenreDto.class);

        System.out.println(author.getGenre());
    }

    @Transactional(readOnly = true)
    public void fetchAuthorByNameAndAgeAsEntityJpql() {
        Author author = authorRepository.findByNameAndAge("Joana Nimar", 34, Author.class);

        System.out.println(author);
    }

    @Transactional(readOnly = true)
    public void fetchAuthorByNameAndAgeAsDtoNameEmailJpql() {
        AuthorNameEmailDto author = authorRepository.findByNameAndAge("Joana Nimar", 34, AuthorNameEmailDto.class);

        System.out.println(author.getEmail() + ", " + author.getName());
    }

    @Transactional(readOnly = true)
    public void fetchAuthorByNameAndAgeAsDtoGenreJpql() {
        AuthorGenreDto author = authorRepository.findByNameAndAge("Joana Nimar", 34, AuthorGenreDto.class);

        System.out.println(author.getGenre());
    }

    @Transactional(readOnly = true)
    public void fetchAuthorsAsEntitiesJpql() {
        List<Author> authors = authorRepository.findByGenre("Anthology", Author.class);

        System.out.println(authors);
    }

    @Transactional(readOnly = true)
    public void fetchAuthorsAsDtoJpql() {
        List<AuthorNameEmailDto> authors = authorRepository.findByGenre("Anthology", AuthorNameEmailDto.class);

        authors.forEach(a -> System.out.println(a.getName() + ", " + a.getEmail()));
    }

}
