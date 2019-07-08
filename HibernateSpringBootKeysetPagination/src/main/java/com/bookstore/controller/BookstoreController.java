package com.bookstore.controller;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import com.bookstore.service.BookstoreService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/authors/{id}/{limit}")
    public List<Author> fetchAuthors(@PathVariable long id, @PathVariable int limit) {

        return bookstoreService.fetchNextPage(id, limit);
    }

    @GetMapping("/dto/authors/{id}/{limit}")
    public List<AuthorDto> fetchAuthorsDto(@PathVariable long id, @PathVariable int limit) {

        return bookstoreService.fetchNextPageDto(id, limit);
    }
}
