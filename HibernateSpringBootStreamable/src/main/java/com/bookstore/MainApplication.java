package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("\n\nFetch Author as Streamable:");
            bookstoreService.fetchAuthorsAsStreamable();

            System.out.println("\n\nFetch Author DTO as Streamable:");
            bookstoreService.fetchAuthorsDtoAsStreamable();

            System.out.println("\n\nDON'T DO THIS! Fetch all columns just to drop a part of them:");
            bookstoreService.fetchAuthorsNames();

            System.out.println("\n\nDON'T DO THIS! Fetch more rows than needed just to throw away a part of it:");
            bookstoreService.fetchAuthorsOlderThan40();

            System.out.println("\n\nDON'T DO THIS! Trigger more SQL statements than necessary:");
            bookstoreService.fetchAuthorsByGenreAndAge();
        };
    }
}
