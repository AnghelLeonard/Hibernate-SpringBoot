package com.jpa;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertDeleteCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertUpdateCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CountSQLStatementsApplication {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(CountSQLStatementsApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            userService.userOperationsWithoutTransactional();
            
            SQLStatementCountValidator.reset();      
            userService.userOperationsWithTransactional();

            // allow the transaction to commit
            // a total of 2 statements instead of 5 as in the case of no explicit transaction
            assertInsertCount(1);
            assertUpdateCount(0);
            assertDeleteCount(1);
            assertSelectCount(0);
        };
    }
}
