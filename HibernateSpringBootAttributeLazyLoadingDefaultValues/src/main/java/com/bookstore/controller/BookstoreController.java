package com.bookstore.controller;

import com.bookstore.service.BookstoreService;
import com.bookstore.entity.Author;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/author/{id}")
    public Author fetchAuthors(@PathVariable long id) {
        return bookstoreService.fetchAuthor(id);
    }

}
