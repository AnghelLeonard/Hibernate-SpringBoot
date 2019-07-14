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
    
    @Transactional(readOnly = true)
    public void fetchBooksAndAuthors() {

        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            Author author = book.getAuthor();
            System.out.println("Book: " + book.getTitle() + " Author: " + author.getName());
        }
    }
    
    @Transactional(readOnly = true)
    public void fetchAuthorsAndBooks() {

        List<Author> authors = authorRepository.findAll();

        for (Author author: authors) {
            List<Book> books = author.getBooks();
            System.out.println("Author: " + author.getName() + " Books: " + books);
        }
    }
}
