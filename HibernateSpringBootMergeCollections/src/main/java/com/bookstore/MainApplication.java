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

            System.out.println("------------------- Joana Nimar's Books --------------------");
            List<Book> books = bookstoreService.fetchBooksOfAuthor("Joana Nimar");

            books.forEach(b -> System.out.println(b));

            System.out.println("---------- Books of Joana Nimar Updated Detached ------------");

            // ,update first book title
            books.get(0).setTitle("A History of Ancient Rome");

            // remove second title
            books.remove(1);

            // add a new book
            Book book = new Book();
            book.setTitle("History In 100 Minutes");
            book.setIsbn("005-JN");
            books.add(book);

            books.forEach(b -> System.out.println(b));
            
            System.out.println("----------------- Books of Joana Nimar Merged ----------------");
            bookstoreService.updateBooksOfAuthor("Joana Nimar", books);

            books.forEach(b -> System.out.println(b));
        };
    }
}
