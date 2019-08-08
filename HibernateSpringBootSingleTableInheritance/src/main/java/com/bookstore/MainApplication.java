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
            System.out.println("\n\naddAuthorWithBooks():");
            bookstoreService.addAuthorWithBooks();

            System.out.println("\n\nfetchBookByName():");
            bookstoreService.fetchBookByName();

            System.out.println("\n\nfetchPaperback():");
            bookstoreService.fetchPaperback();
            System.out.println("\n\nfetchEbook():");
            bookstoreService.fetchEbook();

            System.out.println("\n\nfetchBooks():");
            bookstoreService.fetchBooks();
            System.out.println("\n\fetchAuthorAndBooksLazy():");
            bookstoreService.fetchAuthorAndBooksLazy();
            System.out.println("\n\nfetchAuthorAndBooksEager():");
            bookstoreService.fetchAuthorAndBooksEager();
        };
    }
}
