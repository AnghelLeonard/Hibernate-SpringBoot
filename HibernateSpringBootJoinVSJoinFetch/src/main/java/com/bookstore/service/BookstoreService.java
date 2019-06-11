package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import java.util.List;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository,
            BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    // INNER JOIN
    @Transactional(readOnly = true)
    public void fetchAuthorsBooksByPriceInnerJoin() {
        List<Author> authors = authorRepository.fetchAuthorsBooksByPriceInnerJoin(40);

        authors.forEach((e) -> System.out.println(e.getName() + " " + e.getGenre()
                + " | " + e.getBooks())); // causes extra SELECTs and the result is not ok
    }

    // INNER JOIN
    @Transactional(readOnly = true)
    public void fetchBooksAuthorsInnerJoin() {
        List<Book> books = bookRepository.fetchBooksAuthorsInnerJoin();

        books.forEach((e) -> System.out.println(e.getTitle() + ", " + e.getIsbn()
                + " | " + e.getAuthor())); // causes extra SELECTs but the result is ok
    }

    // JOIN FETCH
    public void fetchAuthorsBooksByPriceJoinFetch() {
        List<Author> authors = authorRepository.fetchAuthorsBooksByPriceJoinFetch(40);

        authors.forEach((e) -> System.out.println(e.getName() + " " + e.getGenre()
                + " | " + e.getBooks())); // does not cause extra SELECTs and the result is ok
    }

    // JOIN FETCH
    @Transactional(readOnly = true)
    public void fetchBooksAuthorsJoinFetch() {
        List<Book> books = bookRepository.fetchBooksAuthorsJoinFetch();

        books.forEach((e) -> System.out.println(e.getTitle() + ", " + e.getIsbn()
                + " | " + e.getAuthor())); // does not cause extra SELECTs and the result is ok
    }
}
