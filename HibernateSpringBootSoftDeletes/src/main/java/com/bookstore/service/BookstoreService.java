package com.bookstore.service;

import com.bookstore.repository.BookRepository;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import java.util.List;
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

    @Transactional
    public void softDeleteBook() {
        Author author = authorRepository.findById(4L).get();
        Book book = author.getBooks().get(0);

        author.removeBook(book);
    }

    @Transactional
    public void softDeleteAuthor() {
        Author author = authorRepository.findById(1L).get();

        authorRepository.delete(author);
    }

    @Transactional
    public void restoreBook() {
        bookRepository.restoreById(1L);        
    }

    @Transactional
    public void restoreAuthor() {
        authorRepository.restoreById(1L);
        bookRepository.restoreByAuthorId(1L);
    }

    public void displayAllExceptDeletedAuthors() {
        List<Author> authors = authorRepository.findAll();

        System.out.println("\nAll authors except deleted:");
        authors.forEach(a -> System.out.println("Author name: " + a.getName()));
        System.out.println();
    }

    public void displayAllIncludeDeletedAuthors() {
        List<Author> authors = authorRepository.findAllIncludingDeleted();

        System.out.println("\nAll authors including deleted:");
        authors.forEach(a -> System.out.println("Author name: " + a.getName()));
        System.out.println();
    }

    public void displayAllOnlyDeletedAuthors() {
        List<Author> authors = authorRepository.findAllOnlyDeleted();

        System.out.println("\nAll deleted authors:");
        authors.forEach(a -> System.out.println("Author name: " + a.getName()));
        System.out.println();
    }

    public void displayAllExceptDeletedBooks() {
        List<Book> books = bookRepository.findAll();

        System.out.println("\nAll books except deleted:");
        books.forEach(b -> System.out.println("Book title: " + b.getTitle()));
        System.out.println();
    }

    public void displayAllIncludeDeletedBooks() {
        List<Book> books = bookRepository.findAllIncludingDeleted();

        System.out.println("\nAll books including deleted:");
        books.forEach(b -> System.out.println("Book title: " + b.getTitle()));
        System.out.println();
    }

    public void displayAllOnlyDeletedBooks() {
        List<Book> books = bookRepository.findAllOnlyDeleted();

        System.out.println("\nAll deleted books:");
        books.forEach(b -> System.out.println("Book title: " + b.getTitle()));
        System.out.println();
    }
}
