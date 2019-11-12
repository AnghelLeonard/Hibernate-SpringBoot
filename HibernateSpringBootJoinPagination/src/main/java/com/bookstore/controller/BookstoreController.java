package com.bookstore.controller;

import com.bookstore.dto.AuthorBookDto;
import com.bookstore.service.BookstoreService;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/page/{page}/{size}")
    public Page<AuthorBookDto> fetchPageOfAuthorsWithBooksDtoByGenre(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchPageOfAuthorsWithBooksDtoByGenre(page, size);
    }

    @GetMapping("/slice/{page}/{size}")
    public Slice<AuthorBookDto> fetchSliceOfAuthorsWithBooksDtoByGenre(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchSliceOfAuthorsWithBooksDtoByGenre(page, size);
    }

    @GetMapping("/list/{page}/{size}")
    public List<AuthorBookDto> fetchListOfAuthorsWithBooksDtoByGenre(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchListOfAuthorsWithBooksDtoByGenre(page, size);
    }

    @GetMapping("/page/native/{page}/{size}")
    public Page<AuthorBookDto> fetchPageOfAuthorsWithBooksDtoByGenreNative(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchPageOfAuthorsWithBooksDtoByGenreNative(page, size);
    }

    @GetMapping("/page/dr/{page}/{size}")
    public List<AuthorBookDto> fetchListOfAuthorsWithBooksDtoNativeDenseRank(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchListOfAuthorsWithBooksDtoNativeDenseRank(page, size);
    }
}
