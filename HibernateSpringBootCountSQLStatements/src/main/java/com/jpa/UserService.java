package com.jpa;

import com.vladmihalcea.sql.SQLStatementCountValidator;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertDeleteCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertInsertCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;
import static com.vladmihalcea.sql.SQLStatementCountValidator.assertUpdateCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void userOperationsWithoutTransactional() {
        User user = new User();

        user.setName("Jacky Francisco");
        user.setCity("Banesti");
        user.setAge(24);

        SQLStatementCountValidator.reset();

        userRepository.save(user);   // 1 insert
        user.setCity("Craiova");
        userRepository.save(user);   // 1 update
        userRepository.delete(user); // 1 delete

        // at this point the transaction was committed
        // a total of 5 statements, not very good
        assertInsertCount(1);
        assertUpdateCount(1);
        assertDeleteCount(1);
        assertSelectCount(2);
    }

    @Transactional
    public void userOperationsWithTransactional() {
        User user = new User();

        user.setName("Jacky Francisco");
        user.setCity("Banesti");
        user.setAge(24);
              
        userRepository.save(user);   // 1 insert
        user.setCity("Craiova");
        userRepository.save(user);   // update not triggered since a delete follows
        userRepository.delete(user); // 1 delete        
    }
}
