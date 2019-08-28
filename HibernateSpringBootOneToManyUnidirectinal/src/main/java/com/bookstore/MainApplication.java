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
            System.out.println("\nAdd one author with three books  ...");
            bookstoreService.addAuthorWithBooks();

            System.out.println("\n---------------------------------------------");

            System.out.println("\nAdd new book to an author  ...");
            bookstoreService.addNewBook();

            System.out.println("\n---------------------------------------------");

            System.out.println("\nDelete last book of an author  ...");
            bookstoreService.deleteLastBook();

            System.out.println("\n---------------------------------------------");

            System.out.println("\nDelete first book of an author  ...");
            bookstoreService.deleteFirstBook();
        };
    }
}
