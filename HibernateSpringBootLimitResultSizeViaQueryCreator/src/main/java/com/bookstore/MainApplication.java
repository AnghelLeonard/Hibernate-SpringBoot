package com.bookstore;

import com.bookstore.dto.AuthorDto;
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
            System.out.println("\nFetch the first 5 authors by age equal to 56: \n"
                    + bookstoreService.fetchFirst5ByAge(56)
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors by age greater than or equal to 30: \n"
                    + bookstoreService.fetchFirst5ByAgeGreaterThanEqual(30)
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors by age less than 35: \n"
                    + bookstoreService.fetchFirst5ByAgeLessThan(35)
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors by age equal to 56 ordered descending by name: \n"
                    + bookstoreService.fetchFirst5ByAgeOrderByNameDesc(56)
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors by genre equal to History ordered ascending by age: \n"
                    + bookstoreService.fetchFirst5ByGenreOrderByAgeAsc("History")
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors by age greater than or equal to 40 ordered ascending by name: \n"
                    + bookstoreService.fetchFirst5ByAgeGreaterThanEqualOrderByNameAsc(40)
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors by genre Horror and age less than 50 ordered descending by name: \n"
                    + bookstoreService.fetchFirst5ByGenreAndAgeLessThanOrderByNameDesc("Horror", 50)
                    + "\n\n");

            System.out.println("\nFetch the first 5 authors ordered ascending by age as DTO: \n");
            List<AuthorDto> authors = bookstoreService.fetchFirst5ByOrderByAgeAsc();
            authors.forEach(a -> System.out.println("Author:" + a.getName() + ", " + a.getAge()));
        };
    }
}
