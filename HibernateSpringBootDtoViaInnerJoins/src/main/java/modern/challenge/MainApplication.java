package modern.challenge;

import java.util.List;
import modern.challenge.service.BookstoreService;
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

            System.out.println("\nfetchBooksAndAuthorsJpql: ");
            bookstoreService.fetchBooksAndAuthorsJpql()
                    .forEach((e) -> System.out.println(e.getName() + " | " + e.getTitle()));

            System.out.println("\nfetchBooksAndAuthorsSql: ");
            bookstoreService.fetchBooksAndAuthorsSql()
                    .forEach((e) -> System.out.println(e.getName() + " | " + e.getTitle()));

            System.out.println("\nfetchAuthorsAndBooksJpql: ");
            bookstoreService.fetchAuthorsAndBooksJpql()
                    .forEach((e) -> System.out.println(e.getName() + " | " + e.getTitle()));

            System.out.println("\nfetchAuthorsAndBooksSql: ");
            bookstoreService.fetchAuthorsAndBooksSql()
                    .forEach((e) -> System.out.println(e.getName() + " | " + e.getTitle()));

            System.out.println("\nfindAuthorsAndBooksByGenreAndPriceJpql: ");
            bookstoreService.findAuthorsAndBooksByGenreAndPriceJpql("History", 40)
                    .forEach((e) -> System.out.println(e.getName() + " | " + e.getTitle()));
            
            System.out.println("\nfindAuthorsAndBooksByGenreAndPriceSql: ");
            bookstoreService.findAuthorsAndBooksByGenreAndPriceSql("History", 40)
                    .forEach((e) -> System.out.println(e.getName() + " | " + e.getTitle()));
            
            System.out.println("\nfindBooksAndAuthorsByGenreAndPriceJpql: ");
            bookstoreService.findBooksAndAuthorsByGenreAndPriceJpql("History", 40)
                    .forEach((e) -> System.out.println(e.getName() + " | " + e.getTitle()));
            
            System.out.println("\nfindBooksAndAuthorsByGenreAndPriceSql: ");
            bookstoreService.findBooksAndAuthorsByGenreAndPriceSql("History", 40)
                    .forEach((e) -> System.out.println(e.getName() + " | " + e.getTitle()));
        };
    }
}
