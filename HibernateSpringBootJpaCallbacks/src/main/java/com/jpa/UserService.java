package com.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void insertUser() {

        User user = new User();

        user.setName("Jacky Francisco");
        user.setCity("Banesti");
        user.setAge(24);

        // this will call @Pre/PostPersist callback
        userRepository.save(user);
    }

    @Transactional
    public void selectUpdateDeleteUser() {

        // this will call @PostLoad (the user is in persistent context)
        User user = userRepository.findById(1L).get();

        // force update, so @Pre/PostUpdate will be called
        user.setCity("Craiova");
        userRepository.saveAndFlush(user);

        // this will call @Pre/PostRemove callback
        userRepository.delete(user);
    }
}
