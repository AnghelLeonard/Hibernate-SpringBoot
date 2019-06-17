package com.bookstore;

import com.bookstore.service.BookstoreService;
import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertDeleteCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertUpdateCount;
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
            
            bookstoreService.authorOperationsWithoutTransactional();
            
            SQLStatementCountValidator.reset();      
            bookstoreService.authorOperationsWithTransactional();

            // allow the transaction to commit
            // a total of 2 statements instead of 5 as in the case of no explicit transaction
            assertInsertCount(1);
            assertUpdateCount(0);
            assertDeleteCount(1);
            assertSelectCount(0);
        };
    }
}
