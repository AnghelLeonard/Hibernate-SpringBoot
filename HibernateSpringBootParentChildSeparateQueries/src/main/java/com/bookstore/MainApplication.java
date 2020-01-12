package com.bookstore;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
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
            
            System.out.println("\nFetch author");
            System.out.println("------------");

            Author author = bookstoreService.fetchAuthor(4L);
            
            System.out.println("\nFetch books of author");
            System.out.println("---------------------");
                        
            System.out.println("\nBad approach:");
            List<Book> booksBad = bookstoreService.fetchBooksOfAuthorBad(author);
            System.out.println("Books: " + booksBad);
            
            System.out.println("\nGood approach:");
            List<Book> booksGood = bookstoreService.fetchBooksOfAuthorGood(author);            
            System.out.println("Books: " + booksGood);
            
            System.out.println("\n==============================");
            System.out.println("==============================");
            
            System.out.println("\nFetch book");
            System.out.println("----------");

            Book book = bookstoreService.fetchBook(4L);
            
            System.out.println("\nFetch author of book");
            System.out.println("---------------------");
                        
            System.out.println("\nBad approach:");
            Author authorBad = bookstoreService.fetchAuthorOfBookBad(book);
            System.out.println("Author: " + authorBad);
            
            System.out.println("\nGood approach:");
            Author authorGood = bookstoreService.fetchAuthorOfBookGood(book);
            System.out.println("Author: " + authorGood);
        };
    }
}
