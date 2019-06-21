package com.bookstore.repository;

import com.bookstore.entity.AuthorSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorSetRepository extends JpaRepository<AuthorSet, Long> {
}
