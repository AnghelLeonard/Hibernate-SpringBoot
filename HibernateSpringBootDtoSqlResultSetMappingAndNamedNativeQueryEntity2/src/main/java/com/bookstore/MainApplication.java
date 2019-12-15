package com.bookstore;

import com.bookstore.entity.Author;
import java.util.List;
import com.bookstore.service.BookstoreService;
import java.util.Arrays;
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

            List<Author> authors = bookstoreService.fetchAuthors();
            System.out.println(authors);
            
            System.out.println("==========================================");

            List<Object[]> authorsArray = bookstoreService.fetchAuthorWithBook();
            authorsArray.forEach(a -> System.out.println(Arrays.toString(a)));
        };
    }
}
