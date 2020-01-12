package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository, BookRepository bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author fetchAuthor(long id) {

        return authorRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Book> fetchBooksOfAuthorBad(Author a) {

        Author author = fetchAuthor(a.getId());
        List<Book> books = author.getBooks();

        Hibernate.initialize(books);
        // books.size();

        return books;
    }

    @Transactional(readOnly = true)
    public List<Book> fetchBooksOfAuthorGood(Author a) {

        // Explicit JPQL
        // List<Book> books = bookRepository.fetchByAuthor(a);
        // Query Builder
        List<Book> books = bookRepository.findByAuthor(a);

        return books;
    }

    public Book fetchBook(long id) {

        return bookRepository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public Author fetchAuthorOfBookBad(Book b) {

        Book book = fetchBook(b.getId());
        Author author = book.getAuthor();

        Hibernate.initialize(author);

        return author;
    }

    @Transactional(readOnly = true)
    public Author fetchAuthorOfBookGood(Book b) {

        // Explicit JPQL
        // Author author = authorRepository.fetchByBooks(b);
        // Query Builder
        Author author = authorRepository.findByBooks(b);

        return author;
    }

}
