package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
    @Transactional(readOnly = true)
    @Query("SELECT u.name as name, u.city as city FROM User u WHERE u.surname = ?1")            
    List<UserDetail> fetchBySurname(String surname);
}
