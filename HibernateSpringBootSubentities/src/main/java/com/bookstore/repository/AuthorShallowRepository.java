package com.bookstore.repository;

import com.bookstore.entity.AuthorShallow;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorShallowRepository extends JpaRepository<AuthorShallow, Long> {   
    
    @Transactional(readOnly=true)
    List<AuthorShallow> findByAgeGreaterThanEqual(int age);
}

