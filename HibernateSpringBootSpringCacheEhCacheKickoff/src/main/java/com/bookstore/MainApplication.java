package com.bookstore;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.bookstore.service.BookstoreService;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
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

            bookstoreService.persistTwoBooks();
            
            System.out.println("\n\n===========================================");
            
            System.out.println("First call fetchBookByPrice() ................");            
            System.out.println(bookstoreService.fetchBookByPrice());

            System.out.println("Second call fetchBookByPrice() ................");
            System.out.println(bookstoreService.fetchBookByPrice());
            
            System.out.println("\n\n===========================================");
                        
            System.out.println("Call deleteBooks() ................");
            bookstoreService.deleteBooks();
            
            System.out.println("\n\n===========================================");
            
            System.out.println("Fourth call fetchBookByPrice() ................");            
            System.out.println(bookstoreService.fetchBookByPrice());
        };
    }
}
