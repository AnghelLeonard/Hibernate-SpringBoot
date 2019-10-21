package com.bookstore;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.bookstore.service.BookstoreService;

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

            System.out.println("\n\nFetch by multiple IDs via @Query:");
            bookstoreService.fetchByMultipleIdsIn();

            System.out.println("\n\nFetch by multiple IDs via MultipleIdsRepository.byMultipleIds:");
            bookstoreService.fetchByMultipleIds();

            System.out.println("\n\nFetch by multiple IDs via MultipleIdsRepository.fetchInBatchesByMultipleIds:");
            bookstoreService.fetchInBatchesByMultipleIds();

            System.out.println("\n\nFetch by multiple IDs via MultipleIdsRepository.fetchBySessionCheckMultipleIds:");
            bookstoreService.fetchBySessionCheckMultipleIds();

            System.out.println("\n\nFetch by multiple IDs via MultipleIdsRepository.fetchInBatchesBySessionCheckMultipleIds:");
            bookstoreService.fetchInBatchesBySessionCheckMultipleIds();
        };
    }
}
