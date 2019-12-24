package com.bookstore;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
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

            List<Author> authors = bookstoreService.fetchAnthologyAuthors();
            System.out.println(authors);

            System.out.println("\n--------------------------");

            List<AuthorDto> authorsDto = bookstoreService.fetchNicknameAndAgeByGenre();
            System.out.println(authorsDto);
            
            System.out.println("\n--------------------------");
            
            bookstoreService.fetchNicknameGenreAndAgeByGenre();

            System.out.println("\n--------------------------");

            AuthorDto author = bookstoreService.fetchNicknameAndAgeById();
            System.out.println(author);
        };
    }
}
