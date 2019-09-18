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

            System.out.println("\n\nREAD-WRITE MODE:");
            bookstoreService.readWriteMode();
            
            System.out.println("\n\nREAD-ONLY MODE:");
            bookstoreService.readOnlyMode();
            
            System.out.println("\n\nREAD-ONLY MODE (DTO):");
            bookstoreService.readOnlyDtoMode();
            
            System.out.println("\n\nREAD-WRITE MODE (DTO):");
            bookstoreService.readWriteDtoMode();
        };
    }
}
