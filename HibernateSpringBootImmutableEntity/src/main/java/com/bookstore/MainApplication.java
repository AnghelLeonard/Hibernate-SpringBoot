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
            System.out.println("Create new author ...");
            bookstoreService.newAuthor();
            
            System.out.println("Fetch the created author ...");
            bookstoreService.fetchAuthor();
            
            System.out.println("Update author (should not work) ...");
            bookstoreService.updateAuthor();
            
            System.out.println("Delete the author ...");
            bookstoreService.deleteAuthor();
        };
    }
}
