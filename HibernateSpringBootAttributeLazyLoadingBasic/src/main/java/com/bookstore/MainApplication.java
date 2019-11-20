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

            System.out.println("Persisting several authors ...");
            bookstoreService.createAuthors();

            System.out.println("\nFetch authors older than 40  ...");
            List<Author> authorsOld40 = bookstoreService.fetchAuthorsByAgeGreaterThanEqual(40);
            System.out.println(authorsOld40);

            System.out.println("\nFetch the avatar of author with id 1  ...");
            byte[] authorAvatar = bookstoreService.fetchAuthorAvatarViaId(1L);
            System.out.println(authorAvatar.length + " bytes");

            System.out.println("\nN+1 (avoid this)  ...");
            List<Author> authorsDetails = bookstoreService.fetchAuthorsDetailsByAgeGreaterThanEqual(40);
            System.out.println(authorsDetails);

            System.out.println("\nFetching DTO including avatars  ...");
            List<AuthorDto> authorsWithAvatars = bookstoreService.fetchAuthorsWithAvatarsByAgeGreaterThanEqual(40);
            authorsWithAvatars.forEach(a -> System.out.println(a.getName() + ", " + a.getAvatar()));
        };
    }
}
