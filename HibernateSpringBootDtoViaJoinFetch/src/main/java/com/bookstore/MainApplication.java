package com.bookstore;

import com.bookstore.service.BookstoreService;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.bookstore.interfacebased.dto.MirrorAuthorDto;

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
            System.out.println("\n\nFetch authors as read-only entities:");
            bookstoreService.fetchAuthorsAsReadOnlyEntities();

            System.out.println("\n\nFetch authors via interface-based projection that mirrors the entity:");
            bookstoreService.fetchAuthorsMirrorInterface();

            System.out.println("\n\nFetch authors via interface-based projection (DTO):");
            bookstoreService.fetchAuthorsDtoInterface();
        };
    }
}
