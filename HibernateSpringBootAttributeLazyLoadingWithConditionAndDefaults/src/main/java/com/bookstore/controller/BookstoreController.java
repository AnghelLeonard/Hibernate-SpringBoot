package com.bookstore.controller;

import com.bookstore.service.BookstoreService;
import com.bookstore.entity.Author;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.util.List;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/authors")
    public List<Author> fetchAuthors() {
        return bookstoreService.fetchAuthors();
    }

}
