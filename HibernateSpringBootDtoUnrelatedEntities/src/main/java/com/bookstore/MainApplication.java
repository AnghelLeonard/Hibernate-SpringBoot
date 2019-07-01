package com.bookstore;

import com.bookstore.dto.BookstoreDto;
import com.bookstore.service.BookstoreService;
import java.util.List;
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
            List<BookstoreDto> result = bookstoreService.fetchAuthorNameBookTitleWithPrice(42);
            
            result.forEach(r -> System.out.println("Author: " + r.getName() + "  Title: " + r.getTitle()));
        };
    }
}
