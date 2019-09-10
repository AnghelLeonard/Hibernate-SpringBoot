package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository,
            BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Page<Author> fetchWithBooksByGenreCQ() {

        return authorRepository.fetchWithBooksByGenreCQ("Anthology",
                PageRequest.of(0, 2, new Sort(Sort.Direction.ASC, "name")));
    }

    public Page<Author> fetchWithBooksByGenreEG() {

        return authorRepository.fetchWithBooksByGenreEG("Anthology",
                PageRequest.of(0, 2, new Sort(Sort.Direction.ASC, "name")));
    }

    public Page<Book> fetchWithAuthorsByIsbnCQ() {

        return bookRepository.fetchWithAuthorsByIsbnCQ("001-",
                PageRequest.of(0, 2, new Sort(Sort.Direction.ASC, "title")));
    }

    public Page<Book> fetchWithAuthorsByIsbnEG() {

        return bookRepository.fetchWithAuthorsByIsbnEG("001-",
                PageRequest.of(0, 2, new Sort(Sort.Direction.ASC, "title")));
    }
}
