package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    @Autowired
    private BookstoreService bookstoreService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\nCall AuthorRepository#findByAgeGreaterThanAndGenre(), fetch graph:");
            bookstoreService.displayAuthorsAndBooksByAgeAndGenre(40, "Anthology");
            
            System.out.println("\nCall AuthorRepository#findByGenreAndAgeGreaterThan(), load graph:");
            bookstoreService.displayAuthorsAndBooksByGenreAndAge("Anthology", 40);
            
            //bookstoreService.displayBooksAndAuthors();
        };
    }
}
