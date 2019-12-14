package com.bookstore;

import com.bookstore.ds2.BookService;
import com.bookstore.ds1.AuthorService;
import com.bookstore.service.AuthorBookService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookService bookService;
    private final AuthorService authorService;
    private final AuthorBookService authorBookService;

    public MainApplication(BookService bookService, AuthorService authorService,
            AuthorBookService authorBookService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.authorBookService = authorBookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\n Saving authors (check the 'authorsdb' database) ...");
            authorService.persistAuthor();
            authorBookService.persistAuthor();
            
            System.out.println("\n Saving books (check the 'booksdb' database) ...");
            bookService.persistBook();                        
            authorBookService.persistBook();
        };
    }
}
