package com.bookstore;

import com.bookstore.entity.BookReview;
import com.bookstore.service.BookstoreService;
import java.util.logging.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MainApplication {

    private static final Logger logger = Logger.getLogger(MainApplication.class.getName());   
    
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
            BookReview bookReview = new BookReview();
            bookReview.setContent("Very good book!");
            bookReview.setEmail("marinv@gmail.com");

            String response = bookstoreService.postReview(bookReview);
            logger.info(() -> "Response: " + response);
        };
    }
}
