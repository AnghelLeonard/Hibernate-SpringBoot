package com.bookstore;

import com.bookstore.ds1.BookService;
import com.bookstore.ds2.AuthorService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookService bookService;
    private final AuthorService authorService;

    public MainApplication(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\n Saving an author (check the MySQL database) ...");
            authorService.persistAuthor();

            System.out.println("\n Saving a book (check the PostgreSQL database) ...");
            bookService.persistBook();
        };
    }
}
