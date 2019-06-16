package com.bookstore.repository;

import com.bookstore.entity.AuthorGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorGoodRepository extends JpaRepository<AuthorGood, Long> {    
}
