package com.bookstore.controller;

import com.bookstore.entity.Author;
import com.bookstore.service.BookstoreService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.data.domain.Slice;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/page/{page}/{size}")
    public Page<Author> fetchPageOfAuthorsWithBooksByGenre(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchPageOfAuthorsWithBooksByGenre(page, size);
    }

    @GetMapping("/slice/{page}/{size}")
    public Slice<Author> fetchSliceOfAuthorsWithBooksByGenre(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchSliceOfAuthorsWithBooksByGenre(page, size);
    }

    @GetMapping("/list/{page}/{size}")
    public List<Author> fetchListOfAuthorsWithBooksByGenre(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchListOfAuthorsWithBooksByGenre(page, size);
    }
    
    @GetMapping("/page/eg/{page}/{size}")
    public Page<Author> fetchPageOfAuthorsWithBooksByGenreEntityGraph(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchPageOfAuthorsWithBooksByGenreEntityGraph(page, size);
    }
    
    @GetMapping("/page/tuple/{page}/{size}")
    public Page<Author> fetchPageOfAuthorsWithBooksByGenreTuple(
            
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchPageOfAuthorsWithBooksByGenreTuple(page, size);
    }
}
