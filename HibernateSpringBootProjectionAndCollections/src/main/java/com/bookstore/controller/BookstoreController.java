package com.bookstore.controller;

import com.bookstore.spring.dto.AuthorDto;
import com.bookstore.spring.dto.SimpleAuthorDto;
import com.bookstore.service.BookstoreService;
import java.util.List;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/authorsAndbooks/1")
    public List<AuthorDto> fetchAuthorsWithBooksQueryBuilderMechanism() {
        return bookstoreService.fetchAuthorsWithBooksQueryBuilderMechanism();
    }

    @GetMapping("/authorsAndbooks/2")
    public List<AuthorDto> fetchAuthorsWithBooksViaQuery() {
        return bookstoreService.fetchAuthorsWithBooksViaQuery();
    }
    
    @GetMapping("/authorsAndbooks/3")
    public Set<AuthorDto> fetchAuthorsWithBooksViaJoinFetch() {
        return bookstoreService.fetchAuthorsWithBooksViaJoinFetch();
    }

    @GetMapping("/authorsAndbooks/4")
    public List<SimpleAuthorDto> fetchAuthorsWithBooksViaQuerySimpleDto() {
        return bookstoreService.fetchAuthorsWithBooksViaQuerySimpleDto();
    }
    
    @GetMapping("/authorsAndbooks/5")
    public List<Object[]> fetchAuthorsWithBooksViaArrayOfObjects() {
        return bookstoreService.fetchAuthorsWithBooksViaArrayOfObjects();
    }       
    
    @GetMapping("/authorsAndbooks/6")
    public List<com.bookstore.transform.dto.AuthorDto> fetchAuthorsWithBooksViaArrayOfObjectsAndTransformToDto() {
        return bookstoreService.fetchAuthorsWithBooksViaArrayOfObjectsAndTransformToDto();
    }
}
