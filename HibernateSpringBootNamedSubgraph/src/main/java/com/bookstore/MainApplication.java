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
            System.out.println("\nCall AuthorRepository#findAll():");
            bookstoreService.displayAuthorsWithBooksAndPublishers();

            System.out.println("\nCall AuthorRepository#findByAgeLessThanOrderByNameDesc(int age):");
            bookstoreService.displayAuthorsByAgeWithBooksAndPublishers(25);

            System.out.println("\nCall AuthorRepository#fetchAllAgeBetween20And40():");
            bookstoreService.displayAuthorsByAgeBetween20And40WithBooksAndPublishers();

            System.out.println("\nCall AuthorRepository#findAll(Specification):");
            bookstoreService.displayAuthorsWithBooksAndPublishersWithSpec();

            System.out.println("\nCall PublisherRepository#findAll():");
            bookstoreService.displayPublishersWithBooksAndAuthors();

            System.out.println("\nCall PublisherRepository#findByIdLessThanOrderByCompanyDesc():");
            bookstoreService.displayPublishersByIdWithBooksAndAuthors();

            System.out.println("\nCall PublisherRepository#findAll(Specification):");
            bookstoreService.displayPublishersWithBooksAndAuthorsWithSpec();

            System.out.println("\nCall PublisherRepository#fetchAllIdBetween1And3:");
            bookstoreService.displayPublishersByIdBetween1And3WithBooksAndAuthors();
        };
    }
}
