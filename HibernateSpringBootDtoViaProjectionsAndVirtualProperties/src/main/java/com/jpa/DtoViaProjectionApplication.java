package com.jpa;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DtoViaProjectionApplication {

    private static final Logger logger
            = Logger.getLogger(DtoViaProjectionApplication.class.getName());

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DtoViaProjectionApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            User user1 = new User();
            user1.setName("Jacky");
            user1.setSurname("Francisco");
            user1.setAge(24);
            user1.setCountry("Romania");
            user1.setCity("Banesti");
            user1.setSsn(9009765644L);

            User user2 = new User();
            user2.setName("Tyler");
            user2.setSurname("Francisco");
            user2.setAge(21);
            user2.setCountry("US");
            user2.setCity("Texas");
            user2.setSsn(304989723L);

            User user3 = new User();
            user3.setName("Marina");
            user3.setSurname("Francisco");
            user3.setAge(26);
            user3.setCountry("Romania");
            user3.setCity("Cluj");
            user3.setSsn(342424234L);

            User user4 = new User();
            user4.setName("Kelly");
            user4.setSurname("Francisco");
            user4.setAge(22);
            user4.setCountry("Romania");
            user4.setCity("Brasov");
            user4.setSsn(342342667L);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);

            List<UserDetail> users = userRepository.fetchBySurname("Francisco");

            logger.info(() -> "Number of users:" + users.size());

            for (UserDetail user : users) {
                logger.info(() -> "User:" + user.getName() + ", "
                        + user.livingin() + ", " + user.status() + ", " + user.sessionid());
            }
        };
    }
}
