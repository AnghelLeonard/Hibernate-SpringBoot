package com.bookstore.repository;

import com.bookstore.entity.AuthorDeep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDeepRepository extends JpaRepository<AuthorDeep, Long> {
}
