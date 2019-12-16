package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

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

            System.out.println("\n--------------------------------------");
            System.out.println("Calling named queries");
            System.out.println("--------------------------------------\n");

            System.out.println("Find all authors:");
            List<Author> allAuthors = bookstoreService.fetchAllAuthors();
            allAuthors.forEach(System.out::println);
            
            System.out.println("\nFind author by name and age:");
            Author authorNameAge = bookstoreService.fetchAuthorByNameAndAge();
            System.out.println(authorNameAge);

            /* causes exception
            System.out.println("\nFind authors ordered descending by name via Sort:");
            List<Author> sortedAuthors = bookstoreService.fetchAuthorsViaSort();
            sortedAuthors.forEach(System.out::println);
            */
 
           /* causes exception
            System.out.println("\nFind authors older than 30 ordered descending by name via Sort:");
            List<Author> sortedWhereAuthors = bookstoreService.fetchAuthorsViaSortWhere();
            sortedWhereAuthors.forEach(System.out::println);
            */
           
            System.out.println("\nFind page of authors ordered descending by name via Pageable:");
            Page<Author> pageAuthors = bookstoreService.fetchAuthorsPageSort();
            pageAuthors.forEach(System.out::println);

            System.out.println("\nFind page of authors older than 30 ordered descending by name via Pageable:");
            Page<Author> pageWhereAuthors = bookstoreService.fetchAuthorsPageSortWhere();
            pageWhereAuthors.forEach(System.out::println);
            
            System.out.println("\nFind slice of authors ordered descending by name via Pageable:");
            Slice<Author> sliceAuthors = bookstoreService.fetchAuthorsSliceSort();
            sliceAuthors.forEach(System.out::println);
            
            System.out.println("\nFind slice of authors older than 30 ordered descending by name via Pageable:");
            Slice<Author> sliceWhereAuthors = bookstoreService.fetchAuthorsSliceSortWhere();
            sliceWhereAuthors.forEach(System.out::println);

            System.out.println("\n--------------------------------------");
            System.out.println("Calling named native queries");
            System.out.println("--------------------------------------");

            System.out.println("Find all authors (native):");
            List<Author> allAuthorsNative = bookstoreService.fetchAllAuthorsNative();
            allAuthorsNative.forEach(System.out::println);
            
            System.out.println("\nFind author by name and age (native):");
            Author authorNameAgeNative = bookstoreService.fetchAuthorByNameAndAgeNative();
            System.out.println(authorNameAgeNative);

            /* causes exception
            System.out.println("\nFind authors ordered descending by name via Sort (native):");
            List<Author> sortedAuthorsNative = bookstoreService.fetchAuthorsViaSortNative();
            sortedAuthorsNative.forEach(System.out::println);
            */           

            /* causes exception
            System.out.println("\nFind authors older than 30 ordered descending by name via Sort (native):");
            List<Author> sortedWhereAuthorsNative = bookstoreService.fetchAuthorsViaSortWhereNative();
            sortedWhereAuthorsNative.forEach(System.out::println);
            */
                       
            /* causes exception
            System.out.println("\nFind page of authors ordered descending by name via Pageable (native):");
            Page<Author> pageAuthorsNative = bookstoreService.fetchAuthorsPageSortNative();
            pageAuthorsNative.forEach(System.out::println);
            */

            System.out.println("\nFind page of authors older than 30 ordered descending by name via Pageable (native):");
            Page<Author> pageWhereAuthorsNative = bookstoreService.fetchAuthorsPageSortWhereNative();
            pageWhereAuthorsNative.forEach(System.out::println);
           
            System.out.println("\nFind slice of authors ordered descending by name via Pageable (native):");
            Slice<Author> sliceAuthorsNative = bookstoreService.fetchAuthorsSliceSortNative();
            sliceAuthorsNative.forEach(System.out::println);
            
            System.out.println("\nFind slice of authors older than 30 ordered descending by name via Pageable (native):");
            Slice<Author> sliceWhereAuthorsNative = bookstoreService.fetchAuthorsSliceSortWhereNative();
            sliceWhereAuthorsNative.forEach(System.out::println);            
        };
    }
}
