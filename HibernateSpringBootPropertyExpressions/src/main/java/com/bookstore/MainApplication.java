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
            System.out.println("\n\nFetch the author of the given book:");
            bookstoreService.fetchAuthorByBook();

            System.out.println("\n\nFetch the books of the given author:");
            bookstoreService.fetchBooksByAuthor();

            System.out.println("\n\nFetch the author of the book having the given review:");
            bookstoreService.fetchAuthorByBooksReviews();
            
            System.out.println("\n\nFetch the author (DTO) of the given book:");
            bookstoreService.fetchAuthorByBookDto();
            
            System.out.println("\n\nFetch the books (DTO) of the given author:");
            bookstoreService.fetchBooksByAuthorDto();

            System.out.println("\n\nFetch the author (DTO) of the book having the given review:");
            bookstoreService.fetchAuthorByBooksReviewsDto();
        };
    }
}
