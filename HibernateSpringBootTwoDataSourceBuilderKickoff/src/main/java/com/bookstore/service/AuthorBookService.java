package com.bookstore.service;

import com.bookstore.ds1.Author;
import com.bookstore.ds1.AuthorRepository;
import com.bookstore.ds2.Book;
import com.bookstore.ds2.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorBookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorBookService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author persistAuthor() {
        Author author = new Author();

        author.setName("Alicia Tom");
        author.setGenre("Anthology");
        author.setAge(32);
        author.setBooks("Anthology Of 1970");

        return authorRepository.save(author);
    }

    public Book persistBook() {
        Book book = new Book();

        book.setIsbn("001-AT");
        book.setTitle("Antholoy Of 1970");
        book.setAuthors("Alicia Tom");

        return bookRepository.save(book);
    }
}
