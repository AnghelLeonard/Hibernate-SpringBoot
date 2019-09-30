package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.enums.GenreType;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void persistNewAuthor() {
        Author author = new Author();

        author.setName("Maryus Yarn");
        author.setAge(34);
        author.setGenre(GenreType.HISTORY);

        authorRepository.save(author);
    }

    public void fetchAuthor() {
        Author author = authorRepository.findById(1L).orElseThrow();
        System.out.println(author);
    }
}
