package com.bookstore.controller;

import com.bookstore.entity.AuthorDeep;
import com.bookstore.entity.AuthorShallow;
import com.bookstore.service.BookstoreService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/authors/shallow")
    public List<AuthorShallow> fetchAuthorsShallow() {
        return bookstoreService.fetchAuthorsShallow();
    }

    @GetMapping("/authors/deep")
    public List<AuthorDeep> fetchAuthorsDeep() {
        return bookstoreService.fetchAuthorsDeep();
    }
}
