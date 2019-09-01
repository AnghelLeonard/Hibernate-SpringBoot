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
            System.out.println("\nNamed stored procedure ...");
            bookstoreService.fetchAnthologyAuthorsViaNamedStoredProcedure();

            System.out.println("\n---------------------------------------------------------");

            System.out.println("\nStored procedure ...");
            bookstoreService.fetchAnthologyAuthorsViaStoredProcedure();

            System.out.println("\n---------------------------------------------------------");

            System.out.println("\nStored procedure and DTO ...");
            bookstoreService.fetchAnthologyDtoAuthorsViaStoredProcedure();
        };
    }
}
