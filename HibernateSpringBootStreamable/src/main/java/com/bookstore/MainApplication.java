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

            System.out.println("\n\nUpdate all the fetched authors, filter them and map to DTO:");
            bookstoreService.fetchSomeAuthorsAsDTOAndUpdate();

            System.out.println("\n\nFetch authors and partition them by age:");
            bookstoreService.fetchAuthorsByGenrePartitioningByAge();

            System.out.println("\n\nFetch authors names, uppercase and join them in a String:");
            bookstoreService.fetchAuthorsNamesAsString();

            System.out.println("\n\nDON'T DO THIS! Fetching all columns just to drop a part of them:");
            bookstoreService.fetchAuthorsNames();

            System.out.println("\n\nDON'T DO THIS! Fetching all rows just to throw away a part of it:");
            bookstoreService.fetchAuthorsOlderThan40();

            System.out.println("\n\nDON'T DO THIS! Trigger more SQL statements than necessary:");
            bookstoreService.fetchAuthorsByGenreAndAge();
        };
    }
}
