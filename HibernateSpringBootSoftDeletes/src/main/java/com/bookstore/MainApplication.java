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
            
            System.out.println("\n-----------------------");
            System.out.println("---------REMOVE--------");
            System.out.println("-----------------------\n");
            bookstoreService.softDeleteAuthor();
            bookstoreService.softDeleteBook();
           
            bookstoreService.displayAllIncludeDeletedAuthors();
            bookstoreService.displayAllExceptDeletedAuthors();
            bookstoreService.displayAllOnlyDeletedAuthors();

            bookstoreService.displayAllIncludeDeletedBooks();
            bookstoreService.displayAllExceptDeletedBooks();
            bookstoreService.displayAllOnlyDeletedBooks();
            
            System.out.println("\n-----------------------");
            System.out.println("---------RESTORE-------");
            System.out.println("-----------------------\n");            
            bookstoreService.restoreAuthor();
            bookstoreService.restoreBook();
           
            bookstoreService.displayAllIncludeDeletedAuthors();
            bookstoreService.displayAllExceptDeletedAuthors();
            bookstoreService.displayAllOnlyDeletedAuthors();

            bookstoreService.displayAllIncludeDeletedBooks();
            bookstoreService.displayAllExceptDeletedBooks();
            bookstoreService.displayAllOnlyDeletedBooks();
        };
    }
}
