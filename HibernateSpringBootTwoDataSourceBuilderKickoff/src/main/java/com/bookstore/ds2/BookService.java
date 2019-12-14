package com.bookstore.ds2;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book persistBook() {
        Book book = new Book();

        book.setIsbn("001-JN");
        book.setTitle("A History of Ancient Prague");
        book.setAuthors("Joana Nimar");

        return bookRepository.save(book);
    }
}
