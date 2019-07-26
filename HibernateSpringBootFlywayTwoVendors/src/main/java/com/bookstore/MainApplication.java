package com.bookstore;

import com.bookstore.ds1.AuthorService;
import com.bookstore.ds2.BookService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final AuthorService authorService;
    private final BookService bookService;

    public MainApplication(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
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
