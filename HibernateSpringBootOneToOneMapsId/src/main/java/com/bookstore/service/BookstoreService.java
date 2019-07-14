package com.bookstore.service;

import com.bookstore.repository.BookRepository;
import com.bookstore.repository.AuthorRepository;
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

    @Transactional
    public void newBookOfAuthor() {

        Author author = authorRepository.findById(1L).orElseThrow();

        Book book = new Book();
        book.setTitle("A History of Ancient Prague");
        book.setIsbn("001-JN");

        // this will set the id of the book as the id of the author
        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book fetchBookByAuthorId() {

        Author author = authorRepository.findById(1L).orElseThrow();

        return bookRepository.findById(author.getId()).orElseThrow();
    }
}
