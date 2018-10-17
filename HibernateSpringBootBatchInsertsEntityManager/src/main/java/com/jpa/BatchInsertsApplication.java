package com.jpa;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BatchInsertsApplication {

    @Autowired
    private Dao dao;

    public static void main(String[] args) {
        SpringApplication.run(BatchInsertsApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            User user_1 = new User();
            user_1.setId(1L);
            user_1.setName("Jacky Francisco");
            user_1.setCity("Banesti");
            user_1.setAge(24);
            
            User user_2 = new User();
            user_2.setId(2L);
            user_2.setName("Caludiu George");
            user_2.setCity("Brasov");
            user_2.setAge(31);
            
            User user_3 = new User();
            user_3.setId(3L);
            user_3.setName("Marius Botocoala");
            user_3.setCity("Sibiu");
            user_3.setAge(22);
            
            User user_4 = new User();
            user_4.setId(4L);
            user_4.setName("Serban D");
            user_4.setCity("Constanta");
            user_4.setAge(52);
            
            User user_5 = new User();
            user_5.setId(5L);
            user_5.setName("Marina Acoolea");
            user_5.setCity("Brasov");
            user_5.setAge(22);
            
            User user_6 = new User();
            user_6.setId(6L);
            user_6.setName("Tayler Durden");
            user_6.setCity("Dallas");
            user_6.setAge(20);
            
             User user_7 = new User();
            user_7.setId(7L);
            user_7.setName("Andy T");
            user_7.setCity("Mures");
            user_7.setAge(54);

            List<User> users = 
                    Arrays.asList(user_1, user_2, user_3, user_4, user_5, user_6, user_7);
            
            dao.saveInBatch(users);
        };
    }
}
