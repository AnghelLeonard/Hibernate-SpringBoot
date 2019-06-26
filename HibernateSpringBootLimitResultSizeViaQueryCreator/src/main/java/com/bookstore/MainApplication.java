package com.bookstore;

import com.bookstore.dto.AuthorDto;
import com.bookstore.service.BookstoreService;
import java.util.List;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

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
            System.out.println("\nTop 5 by age equal to 56: \n"
                    + bookstoreService.fetchTop5ByAge(56));
            
            System.out.println("\nFirst 5 by age equal with 56 sorted by name: \n"
                    + bookstoreService.fetchFirst5ByAge(56, new Sort(Sort.Direction.ASC, "name")));
            
            System.out.println("\nTop 3 by age greater than or equal to 30: \n"
                    + bookstoreService.fetchTop3ByAgeGreaterThanEqual(30));
            
            System.out.println("\nFirst 3 by age greater than 40 sorted by name: \n"
                    + bookstoreService.fetchFirst3ByAgeGreaterThan(40, new Sort(Sort.Direction.ASC, "name")));
            
            System.out.println("\nFind first 5 by age desc: \n"
                    + bookstoreService.fetchFirst5ByOrderByAgeDesc());
            
            System.out.println("\nFind top 3 by age desc: \n"
                    + bookstoreService.fetchTop3ByOrderByAgeDesc());
            
            System.out.println("\nFind top 3 by age asc as DTO: \n");
            List<AuthorDto> authors = bookstoreService.fetchTop3ByOrderByAgeAsc();
            authors.forEach(a -> System.out.println("Author:" + a.getName() + ", " + a.getAge()));
        };
    }
}
