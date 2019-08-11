package com.bookstore.controller;

import com.bookstore.entity.Author;
import com.bookstore.service.BookstoreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    // The View will NOT force lazy initialization of books
    @RequestMapping("/fetchwithbooks")
    public Author fetchAuthorWithBooks() {

        Author author = bookstoreService.fetchAuthorWithBooks();

        return author;
    }

    // The View will NOT force lazy initialization of books
    @RequestMapping("/fetchwithoutbooks")
    public Author fetchAuthorWithoutBooks() {
        Author author = bookstoreService.fetchAuthorWithoutBooks();
        
        return author;
    }
}