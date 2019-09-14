package com.bookstore.controller;

import com.bookstore.entity.Author;
import com.bookstore.service.BookstoreService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.bookstore.dto.AuthorBookDto;
import org.springframework.data.domain.Slice;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/jpql/page/entity/authorbook/{page}/{size}")
    public Page<Author> fetchAuthorsWithBooksByGenre1(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchAuthorsWithBooksByGenre1(page, size);
    }

    @GetMapping("/jpql/slice/entity/authorbook/{page}/{size}")
    public Slice<Author> fetchAuthorsWithBooksByGenre2(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchAuthorsWithBooksByGenre2(page, size);
    }

    @GetMapping("/jpql/page/dto/authorbook/{page}/{size}")
    public Page<AuthorBookDto> fetchAuthorsDtoWithBooksDtoByGenre1(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchAuthorsDtoWithBooksDtoByGenre1(page, size);
    }

    @GetMapping("/jpql/slice/dto/authorbook/{page}/{size}")
    public Slice<AuthorBookDto> fetchAuthorsDtoWithBooksDtoByGenre2(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchAuthorsDtoWithBooksDtoByGenre2(page, size);
    }

    @GetMapping("/native/dto/authorbook/{page}/{size}")
    public Page<AuthorBookDto> fetchNativeAuthorsDtoWithBooksDtoByGenre(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchNativeAuthorsDtoWithBooksDtoByGenre(page, size);
    }

    @GetMapping("/native/dto/authorbookdr/{start}/{end}")
    public List<AuthorBookDto> fetchAuthorsNativeDtoWithBooksDtoViaDenseRank(
            @PathVariable int start, @PathVariable int end) {

        return bookstoreService.fetchAuthorsNativeDtoWithBooksDtoViaDenseRank(start, end);
    }
}
