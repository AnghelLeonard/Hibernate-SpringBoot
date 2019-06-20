package modern.challenge;

import modern.challenge.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    @Autowired
    private BookstoreService bookstoreService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("Register new author ...");
            bookstoreService.registerAuthor();
            
            System.out.println("Display authors ...");
            bookstoreService.displayAuthors();
            
            System.out.println("Display books ...");
            bookstoreService.displayBooks();                      
        };
    }
}
