package com.bookstore.controller;

import com.bookstore.dto.BookDto;
import com.bookstore.dto.SimpleBookDto;
import com.bookstore.dto.VirtualBookDto;
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

    @GetMapping("/booksAndauthors/1")
    public List<BookDto> fetchBooksWithAuthorsQueryBuilderMechanism() {
        return bookstoreService.fetchBooksWithAuthorsQueryBuilderMechanism();
    }

    @GetMapping("/booksAndauthors/2")
    public List<BookDto> fetchBooksWithAuthorsViaQuery() {
        return bookstoreService.fetchBooksWithAuthorsViaQuery();
    }

    @GetMapping("/booksAndauthors/3")
    public List<SimpleBookDto> fetchBooksWithAuthorsViaQuerySimpleDto() {
        return bookstoreService.fetchBooksWithAuthorsViaQuerySimpleDto();
    }

    @GetMapping("/booksAndauthors/4")
    public List<VirtualBookDto> fetchBooksWithAuthorsViaQueryVirtualDto() {
        return bookstoreService.fetchBooksWithAuthorsViaQueryVirtualDto();
    }
    
    @GetMapping("/booksAndauthors/5")
    public List<Object[]> fetchBooksWithAuthorsViaArrayOfObjects() {
        return bookstoreService.fetchBooksWithAuthorsViaArrayOfObjects();
    }
}
