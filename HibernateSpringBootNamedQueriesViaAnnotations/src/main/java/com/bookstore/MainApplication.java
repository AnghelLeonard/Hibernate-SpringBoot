package com.bookstore;

import java.util.List;
import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.bookstore.entity.Author;

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

            System.out.println("\n--------------------------------------");
            System.out.println("Calling named queries");
            System.out.println("--------------------------------------\n");

            System.out.println("Find all authors older than 30 ordered descending by name:");
            List<Author> authors = bookstoreService.fetchAuthorsGtAgeDescName();
            authors.forEach(System.out::println);

            System.out.println("\nFind author by name and age:");
            Author authorNameAge = bookstoreService.fetchAuthorByNameAndAge();
            System.out.println(authorNameAge);

            System.out.println("\n--------------------------------------");
            System.out.println("Calling named native queries");
            System.out.println("--------------------------------------");

            System.out.println("\nFind all authors older than 30 ordered descending by name (native):");
            List<Author> authorsNative = bookstoreService.fetchAuthorsGtAgeDescNameNative();
            authorsNative.forEach(System.out::println);

            System.out.println("\nFind author by name and age (native):");
            Author authorNameAgeNative = bookstoreService.fetchAuthorByNameAndAgeNative();
            System.out.println(authorNameAgeNative);
        };
    }
}
