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

        authors.forEach((e) -> System.out.println("Author name: " + e.getName() 
                + ", books: " + e.getBooks())); // causes extra SELECTs and the result is not ok
    }

    // INNER JOIN BAD
    @Transactional(readOnly = true)
    public void fetchBooksAuthorsInnerJoinBad() {
        List<Book> books = bookRepository.fetchBooksAuthorsInnerJoinBad();

        books.forEach((e) -> System.out.println("Book title: " + e.getTitle() + ", Isbn: " + e.getIsbn()
                + ", author: " + e.getAuthor())); // causes extra SELECTs but the result is ok
    }
    
    // INNER JOIN GOOD
    @Transactional(readOnly = true)
    public void fetchBooksAuthorsInnerJoinGood() {
        List<Book> books = bookRepository.fetchBooksAuthorsInnerJoinGood();

        books.forEach((e) -> System.out.println("Book title: " + e.getTitle() + ", Isbn: " + e.getIsbn()
                + ", author: " + e.getAuthor())); // causes extra SELECTs but the result is ok
    }

    // JOIN FETCH
    public void fetchAuthorsBooksByPriceJoinFetch() {
        List<Author> authors = authorRepository.fetchAuthorsBooksByPriceJoinFetch(40);

        authors.forEach((e) -> System.out.println("Author name: " + e.getName()
                + ", books: " + e.getBooks())); // does not cause extra SELECTs and the result is ok
    }

    // JOIN FETCH    
    public void fetchBooksAuthorsJoinFetch() {
        List<Book> books = bookRepository.fetchBooksAuthorsJoinFetch();

        books.forEach((e) -> System.out.println("Book title: " + e.getTitle() + ", Isbn:" + e.getIsbn()
                + ", author: " + e.getAuthor())); // does not cause extra SELECTs and the result is ok
    }
}
