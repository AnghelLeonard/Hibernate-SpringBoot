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
            System.out.println("\n\nUsing ORDER BY only in OVER:");
            bookstoreService.fetchAuthorsWithSeqNumber1();
            
            System.out.println("\n\nUsing ORDER BY only in query:");
            bookstoreService.fetchAuthorsWithSeqNumber2();
            
            System.out.println("\n\nUsing ORDER BY in OVER and in query:");
            bookstoreService.fetchAuthorsWithSeqNumber3();
            
            System.out.println("\n\nUsing multiple columns in OVER:");
            bookstoreService.fetchAuthorsWithSeqNumber4();
        };
    }
}
