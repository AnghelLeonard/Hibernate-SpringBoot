package com.bookstore.repository;

import com.bookstore.entity.AuthorBookView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorBookViewRepository extends JpaRepository<AuthorBookView, Long> {
}
