package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public long countByGenre() {

        return authorRepository.countByGenre("Anthology");
    }

    public long deleteByGenre() {

        return authorRepository.deleteByGenre("History");
    }

    public List<Author> removeByGenre() {

        return authorRepository.removeByGenre("Horror");
    }
}
