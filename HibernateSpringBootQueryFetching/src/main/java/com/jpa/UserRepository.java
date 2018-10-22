package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {  
    
    @Transactional(readOnly = true)
    List<User> findByName(String name);
    
    @Transactional(readOnly = true)
    @Query("select u from User u where u.name = ?1")
    List<User> fetchUsersByName(String name);
}

