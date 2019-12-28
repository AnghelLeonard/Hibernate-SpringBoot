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
            System.out.println("\n ------------------ N+1 ----------------- \n");
            
            bookstoreService.displayBooksCausingNPlus1();
            
            System.out.println("-------------------------------------- \n");
            
            bookstoreService.displayBooksOfAuthorsAgeGt45CausingNPlus1();
            
            System.out.println("\n ----------------- LEFT JOIN ----------------- \n");
            
            bookstoreService.displayBooksViaEntityGraph();
            
            System.out.println("-------------------------------------- \n");
            
            bookstoreService.displayBooksOfAuthorsAgeGt45ViaEntityGraph();
        };
    }
}
