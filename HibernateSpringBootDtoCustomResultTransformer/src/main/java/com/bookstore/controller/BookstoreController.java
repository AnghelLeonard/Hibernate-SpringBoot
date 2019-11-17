package com.bookstore.controller;

import com.bookstore.dto.AuthorDto;
import com.bookstore.service.BookstoreService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {
    
    public final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }        
    
    @GetMapping("/authorWithBook")
    public List<AuthorDto> fetchAuthorWithBook() {
        return bookstoreService.fetchAuthorWithBook();
    }
}
