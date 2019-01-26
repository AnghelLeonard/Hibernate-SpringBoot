package com.jpa;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public interface UserRepository extends JpaRepository<User, Long> {  
    
    List<User> findTop5ByAge(int age);     
    List<User> findFirst5ByAge(int age, Sort sort);    
    
    List<User> findTop3ByAgeGreaterThanEqual(int age);
    List<User> findFirst3ByAgeGreaterThan(int age, Sort sort);
    
    List<User> findFirst5ByOrderByAgeDesc();
    List<User> findTop3ByOrderByAgeDesc();    
}

