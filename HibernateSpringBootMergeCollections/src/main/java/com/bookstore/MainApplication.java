package com.bookstore;

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

            System.out.println("\n------------------- Joana Nimar's Books --------------------");
            List<Book> detachedBooks = bookstoreService.fetchBooksOfAuthor("Joana Nimar");

            detachedBooks.forEach(b -> System.out.println(b));

            System.out.println("\n---------- Books of Joana Nimar updated in detached state------------");

            // ,update first book title
            detachedBooks.get(0).setTitle("A History of Ancient Rome");

            // remove second title
            detachedBooks.remove(1);

            // add a new book
            Book book = new Book();
            book.setTitle("History In 100 Minutes");
            book.setIsbn("005-JN");
            detachedBooks.add(book);

            detachedBooks.forEach(b -> System.out.println(b));
            
            System.out.println("\n----------------- Merging books of Joana Nimar ----------------");
            bookstoreService.updateBooksOfAuthor("Joana Nimar", detachedBooks);

            System.out.println("\n----------------- Books of Joana Nimar After Merge ----------------");
            List<Book> books = bookstoreService.fetchBooksOfAuthor("Joana Nimar");
            
            books.forEach(b -> System.out.println(b));
        };
    }
}
