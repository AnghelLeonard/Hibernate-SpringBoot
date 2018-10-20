package com.jpa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void newUser() throws IOException {
        User user = new User();
        user.setId(1L);
        user.setName("Jacky Francisco");
        user.setCity("Banesti");
        user.setAge(24);

        // something like this will read the avatar from disk in a byte[]
        File file = new File("jacky.jpg");
        byte[] avatar = Files.readAllBytes(file.toPath());

        user.setAvatar(avatar);

        userRepository.save(user);
    }

    @Transactional(readOnly=true)
    public User fetchUserWithoutAvatar() {
                
        return userRepository.findById(1L).get();
    }
    
    @Transactional(readOnly=true)
    public User fetchUserWithAvatar() {           
        
        Optional<User> user = userRepository.findById(1L);
        
        // this is loaded LAZY
        user.get().getAvatar();
        
        return user.get();
    }    
}