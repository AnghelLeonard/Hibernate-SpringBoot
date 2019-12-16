package com.bookstore;

import com.bookstore.dto.AuthorNameAge;
import java.util.List;
import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.bookstore.entity.Author;
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

            System.out.println("Find all authors order by name descending:");
            List<Author> authorsDesc = bookstoreService.fetchAllAuthorsDesc();
            authorsDesc.forEach(System.out::println);

            System.out.println("\nFind all authors order by name ascending (via Sort):");
            List<Author> authorsSorted = bookstoreService.fetchAllAuthorsSorted();
            authorsSorted.forEach(System.out::println);

            System.out.println("\nFind a page of authors order by name descending:");
            Page<Author> pageOfAuthors = bookstoreService.fetchAuthorsPageDesc();
            pageOfAuthors.getContent().forEach(System.out::println);

            System.out.println("\nFind author by name and age:");
            Author authorNameAge = bookstoreService.fetchAuthorByNameAndAge();
            System.out.println(authorNameAge);
            
            
            
            
            
            
            
            System.out.println("\nFind all authors order by name ascending (via Sort) (native):");
            List<Author> authorsSortedNative = bookstoreService.fetchAllAuthorsSortedNative();
            authorsSortedNative.forEach(System.out::println);

            System.out.println("\nFind all names and ages:");
            List<AuthorNameAge> namesAndAges = bookstoreService.fetchAuthorsNamesAndAges();
            for (AuthorNameAge author : namesAndAges) {
                System.out.println("Author name: " + author.getName()
                        + " | Age: " + author.getAge());
            }
        };
    }
}
