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
            System.out.println("\n\nUsing NTILE(2):");
            bookstoreService.fetchAuthorsWithNTile1();
            
            System.out.println("\n\nUsing NTILE(3):");
            bookstoreService.fetchAuthorsWithNTile2();                       
            
            System.out.println("\n\nUsing NTILE(3) AND PARTITION BY:");
            bookstoreService.fetchAuthorsWithNTile3();                       
        };
    }
}
