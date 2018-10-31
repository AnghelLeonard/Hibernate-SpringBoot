package com.jpa;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class UserService {

    private static final Logger logger 
            = Logger.getLogger(UserService.class.getName());   
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void updateUser() {

        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                logger.info(() -> "Right after commit ...");
                // Right after commit do other stuff but
                // keep in mind that the connection will not
                // return to pool connection until this code is done
                // So, avoid time-consuming tasks here                 
            }
        });

        User user = userRepository.findById(1L).get();

        user.setAge(26);
    }
}
