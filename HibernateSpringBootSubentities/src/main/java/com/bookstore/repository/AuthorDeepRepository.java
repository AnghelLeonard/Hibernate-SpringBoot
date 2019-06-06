package com.bookstore.repository;

import com.bookstore.entity.AuthorDeep;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorDeepRepository extends JpaRepository<AuthorDeep, Long> {

    @Transactional(readOnly = true)
    List<AuthorDeep> findByAgeGreaterThanEqual(int age);
}
