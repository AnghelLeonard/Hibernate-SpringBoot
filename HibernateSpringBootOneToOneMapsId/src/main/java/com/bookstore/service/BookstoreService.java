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

    public void newAuthor() {

        Author author = new Author();
        author.setName("Joana Nimar");
        author.setGenre("History");
        author.setAge(34);

        authorRepository.save(author);
    }

    @Transactional
    public void newBookOfAuthor() {

        Author author = authorRepository.findByName("Joana Nimar");

        Book book = new Book();
        book.setTitle("A History of Ancient Prague");
        book.setIsbn("001-JN");
        book.setAuthor(author);// this will set the ID of book as the ID of the author

        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book fetchBookByAuthorId() {

        Author author = authorRepository.findByName("Joana Nimar");

        return bookRepository.findById(author.getId()).orElseThrow();
    }
}
