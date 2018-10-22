package com.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository; 
    
    @Transactional
    public void updateUserNameViaRedundantSave(){
        User user = userRepository.findById(1L).get();
        
        user.setName("Hulyo G");
        userRepository.save(user); 
    }
    
    @Transactional
    public void updateUserNameRecommended(){
        User user = userRepository.findById(1L).get();
        
        user.setName("Magic J");        
    }
    
}
