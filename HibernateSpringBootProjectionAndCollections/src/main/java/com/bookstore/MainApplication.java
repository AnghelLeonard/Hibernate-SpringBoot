package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    @Autowired
    private BookstoreService bookstoreService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("\n\nFetch authors with books via query builder mechanism");
            System.out.println("-----------------------------------------------------------------");
            bookstoreService.fetchAuthorsWithBooksQueryBuilderMechanism();

            System.out.println("\n\nFetch authors with books via JPQL query");
            System.out.println("-----------------------------------------------------------------");
            bookstoreService.fetchAuthorsWithBooksViaQuery();

            System.out.println("\n\nFetch authors with books via JOIN FETCH");
            System.out.println("-----------------------------------------------------------------");
            bookstoreService.fetchAuthorsWithBooksViaJoinFetch();

            System.out.println("\n\nFetch authors with books via query and simple DTO");
            System.out.println("-----------------------------------------------------------------");
            bookstoreService.fetchAuthorsWithBooksViaQuerySimpleDto();
        };
    }
}
