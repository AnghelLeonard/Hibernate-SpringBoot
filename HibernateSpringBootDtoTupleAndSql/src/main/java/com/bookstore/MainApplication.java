package com.bookstore;

import java.util.List;
import com.bookstore.service.BookstoreService;
import javax.persistence.Tuple;
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

            List<Tuple> authors = bookstoreService.fetchAuthors();

            System.out.println("Number of authors:" + authors.size());

            for (Tuple author : authors) {
                System.out.println("Author name: " + author.get("name")
                        + " | Age: " + author.get("age"));
            }

            System.out.println("\n-------------------");
            System.out.println(authors.get(0).get("name") instanceof String);
            System.out.println(authors.get(0).get("age") instanceof Integer);
        };
    }
}
