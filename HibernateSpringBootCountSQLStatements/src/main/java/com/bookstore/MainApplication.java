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

            SQLStatementCountValidator.reset();
            bookstoreService.authorOperationsWithoutTransactional();
            // at this point there is no transaction running
            // there are 3 statements         
            assertSelectCount(2);
            assertUpdateCount(1);
            assertInsertCount(0);
            assertDeleteCount(0);

            SQLStatementCountValidator.reset();
            bookstoreService.authorOperationsWithTransactional();
            // allow the transaction to commit
            // there are 2 statements instead of 3
            assertSelectCount(1);
            assertUpdateCount(1);
            assertInsertCount(0);            
            assertDeleteCount(0);           
        };
    }
}
