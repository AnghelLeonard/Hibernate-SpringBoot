package com.jpa;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void populateDatabase() {
        for (long i = 0; i < 1000; i++) {
            User user = new User();            
            user.setName("User_" + i);
            user.setCity("City_" + i);

            userRepository.save(user);
        }
    }
    
    @Transactional(readOnly = true)
    public void streamDatabase() {
        
        long startTime = System.nanoTime();
        try(Stream<User> userStream = userRepository.streamAll()) {
            
            userStream.forEach(System.out::println);
        }
        System.out.println("Total time: " + 
                TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS) + " ms");
    }

}
