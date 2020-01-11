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
            System.out.println("\nCount 'Anthology' authors: \n"
                    + bookstoreService.countByGenre()
                    + "\n\n");
            
            System.out.println("\nDelete 'History' authors: \n"
                    + bookstoreService.deleteByGenre()
                    + "\n\n");
            
            System.out.println("\nDelete and return 'Horror' authors: \n"
                    + bookstoreService.removeByGenre()
                    + "\n\n");

        };
    }
}
