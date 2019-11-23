package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepository;
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
    public void displayAuthorsAndBooks() {

        List<Author> authors = authorRepository.findAll();

        for (Author author : authors) {
            System.out.println("Author: " + author.getName());
            System.out.println("No of books: "
                    + author.getBooks().size() + ", " + author.getBooks());
        }
    }

    @Transactional(readOnly = true)
    public void displayBooksAndAuthors() {

        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            System.out.println("Book: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
        }
    }
}
