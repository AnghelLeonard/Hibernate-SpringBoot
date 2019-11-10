package com.bookstore.repository;

import com.bookstore.summary.AuthorSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorSummaryRepository extends JpaRepository<AuthorSummary, Long> {
}
