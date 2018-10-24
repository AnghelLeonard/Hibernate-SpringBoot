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
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(CountSQLStatementsApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            User user = new User();

            user.setName("Jacky Francisco");
            user.setCity("Banesti");
            user.setAge(24);            

            SQLStatementCountValidator.reset();
            
            userRepository.save(user);   // 1 insert
            user.setCity("Craiova");     
            userRepository.save(user);   // 1 update
            userRepository.delete(user); // 1 delete
            
            assertInsertCount(1);
            assertUpdateCount(1);
            assertDeleteCount(1);            
            
            assertSelectCount(2); 
        };
    }
}