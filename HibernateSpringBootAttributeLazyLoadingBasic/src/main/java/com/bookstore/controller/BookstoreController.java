package com.bookstore.controller;

import com.bookstore.service.BookstoreService;
import com.bookstore.entity.Author;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/create")
    public String create() throws IOException {

        bookstoreService.createAuthors();

        return "created";
    }

    @GetMapping("/author")
    public Author fetchAuthor() {
        return bookstoreService.fetchAuthor();
    }
    
    @GetMapping("/author/details")
    public Author fetchAuthorDetails() {
        return bookstoreService.fetchAuthorDetails();
    }

}