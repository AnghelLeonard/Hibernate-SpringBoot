package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class MainApplication {

    private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String... args) throws Exception {
        var ctx = SpringApplication.run(MainApplication.class, args);

        logger.info("Started ...");
        System.in.read();
        ctx.close();
    }

    @Bean
    public CommandLineRunner scheduleRunner(BookstoreService bookstoreService) {
        return args -> {
            System.out.println("Register new author ...");
            bookstoreService.registerAuthor();

            Thread.sleep(5000);

            System.out.println("Update an author ...");
            bookstoreService.updateAuthor();

            Thread.sleep(5000);
            System.out.println("Update books of an author ...");
            bookstoreService.updateBooks();
        };
    }
}
