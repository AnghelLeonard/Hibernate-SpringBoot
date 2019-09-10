package com.bookstore;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;

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

            Page<Author> authorscq = bookstoreService.fetchWithBooksByGenreCQ();
            authorscq.forEach(System.out::println);
            System.out.println("Number of elements: " + authorscq.getNumberOfElements());
            System.out.println("Total elements: " + authorscq.getTotalElements());

            Page<Author> authorseg = bookstoreService.fetchWithBooksByGenreEG();
            authorseg.forEach(System.out::println);
            System.out.println("Number of elements: " + authorseg.getNumberOfElements());
            System.out.println("Total elements: " + authorseg.getTotalElements());
            
            Page<Book> bookscq = bookstoreService.fetchWithAuthorsByIsbnCQ();
            bookscq.forEach(System.out::println);
            System.out.println("Number of elements: " + bookscq.getNumberOfElements());
            System.out.println("Total elements: " + bookscq.getTotalElements());
            
            Page<Book> bookseg = bookstoreService.fetchWithAuthorsByIsbnEG();
            bookseg.forEach(System.out::println);
            System.out.println("Number of elements: " + bookseg.getNumberOfElements());
            System.out.println("Total elements: " + bookseg.getTotalElements());
        };
    }
}
