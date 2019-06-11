package modern.challenge;

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

            System.out.println("\n\nfetchBooksAuthorsInnerJoin: ");
            bookstoreService.fetchBooksAuthorsInnerJoin();

            System.out.println("\n\nfetchBooksAuthorsJoinFetch: ");
            bookstoreService.fetchBooksAuthorsJoinFetch();

            System.out.println("\n\nfetchAuthorsBooksByPriceInnerJoin (notice that we fetch all books of each author): ");
            bookstoreService.fetchAuthorsBooksByPriceInnerJoin();

            System.out.println("\n\nfetchAuthorsBooksByPriceJoinFetch: ");
            bookstoreService.fetchAuthorsBooksByPriceJoinFetch();
        };
    }
}
