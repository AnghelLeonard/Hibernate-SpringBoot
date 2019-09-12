package com.bookstore.controller;

import com.bookstore.dto.AuthorBookDto;
import com.bookstore.entity.Author;
import com.bookstore.service.BookstoreService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.dto.AuthorDtoBook;
import com.bookstore.dto.AuthorDtoBookDto;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/jpql/entity/authorbook/{page}/{size}")
    public Page<Author> fetchAuthorsWithBooksByGenre(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchAuthorsWithBooksByGenre(page, size);
    }
    
    @GetMapping("/jpql/entity/authordtobook/{page}/{size}")
    public Page<AuthorDtoBook> fetchAuthorsDtoWithBooksByGenre(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchAuthorsDtoWithBooksByGenre(page, size);
    }
    
    @GetMapping("/jpql/entity/authorbookdto/{page}/{size}")
    public Page<AuthorBookDto> fetchAuthorsWithBooksDtoByGenre(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchAuthorsWithBooksDtoByGenre(page, size);
    }
    
    @GetMapping("/jpql/entity/authordtobookdto/{page}/{size}")
    public Page<AuthorDtoBookDto> fetchAuthorsDtoWithBooksDtoByGenre(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchAuthorsDtoWithBooksDtoByGenre(page, size);
    }
    
    @GetMapping("/native/entity/authordtobookdto/{page}/{size}")
    public Page<AuthorDtoBookDto> fetchNativeAuthorsDtoWithBooksDtoByGenre(
            @PathVariable int page, @PathVariable int size) {

        return bookstoreService.fetchNativeAuthorsDtoWithBooksDtoByGenre(page, size);
    }
}


