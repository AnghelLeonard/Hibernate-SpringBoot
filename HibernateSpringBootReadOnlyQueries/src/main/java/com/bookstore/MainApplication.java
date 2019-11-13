package com.bookstore;

import com.bookstore.entity.Author;
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

            System.out.println("Avoid:\n------\n");
            Author authorRW = bookstoreService.fetchAuthorReadWriteMode();
            authorRW.setAge(authorRW.getAge() + 1);
            bookstoreService.updateAuthor(authorRW);

            System.out.println("\n\n=============================\n\n");
            
            System.out.println("Recommended:\n-----------\n");
            Author authorRO = bookstoreService.fetchAuthorReadOnlyMode();
            authorRO.setAge(authorRO.getAge() + 1);
            bookstoreService.updateAuthor(authorRO);
        };
    }
}
