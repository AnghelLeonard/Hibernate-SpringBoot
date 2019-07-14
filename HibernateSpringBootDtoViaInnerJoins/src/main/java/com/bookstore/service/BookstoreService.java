package com.bookstore.service;

import com.bookstore.projection.AuthorNameBookTitle;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import java.util.List;
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

    // Books and authors (JPQL)
    public List<AuthorNameBookTitle> fetchBooksAndAuthorsJpql() {
        return bookRepository.findBooksAndAuthorsJpql();
    }

    // Books and authors (SQL)
    public List<AuthorNameBookTitle> fetchBooksAndAuthorsSql() {
        return bookRepository.findBooksAndAuthorsSql();
    }

    // Authors and books (JPQL)
    public List<AuthorNameBookTitle> fetchAuthorsAndBooksJpql() {
        return authorRepository.findAuthorsAndBooksJpql();
    }

    // Authors and books (SQL)
    public List<AuthorNameBookTitle> fetchAuthorsAndBooksSql() {
        return authorRepository.findAuthorsAndBooksSql();
    }

    // Fetch authors and books filtering by author's genre and book's price (JPQL)
    public List<AuthorNameBookTitle> findAuthorsAndBooksByGenreAndPriceJpql(String genre, int price) {
        return authorRepository.findAuthorsAndBooksByGenreAndPriceJpql(genre, price);
    }

    // Fetch authors and books filtering by author's genre and book's price (SQL)
    public List<AuthorNameBookTitle> findAuthorsAndBooksByGenreAndPriceSql(String genre, int price) {
        return authorRepository.findAuthorsAndBooksByGenreAndPriceSql(genre, price);
    }

    // Fetch books and authors filtering by author's genre and book's price (JPQL)
    public List<AuthorNameBookTitle> findBooksAndAuthorsByGenreAndPriceJpql(String genre, int price) {
        return bookRepository.findBooksAndAuthorsByGenreAndPriceJpql(genre, price);
    }

    // Fetch books and authors filtering by author's genre and book's price (SQL)
    public List<AuthorNameBookTitle> findBooksAndAuthorsByGenreAndPriceSql(String genre, int price) {
        return bookRepository.findBooksAndAuthorsByGenreAndPriceSql(genre, price);
    }
}
