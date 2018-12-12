package com.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Log4j2Application {

    @Autowired
    private UserRepository userRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(Log4j2Application.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            User user = new User();

            user.setName("Jacky Francisco");
            user.setCity("Banesti");
            user.setAge(24);

            userRepository.save(user);
        };
    }
}
