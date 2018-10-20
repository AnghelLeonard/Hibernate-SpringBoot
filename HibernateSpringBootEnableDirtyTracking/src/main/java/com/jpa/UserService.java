package com.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveAndUpdateUser() {

        User user = new User();        
        user.setName("Jacky Francisco");
        user.setCity("Banesti");
        user.setAge(24);

        userRepository.save(user);

        user.setAge(25);
        user.setCity("Brasov");
    }
}
