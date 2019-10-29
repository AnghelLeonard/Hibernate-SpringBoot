package com.bookstore.repository;

import com.bookstore.summary.AuthorSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorSummaryRepository extends JpaRepository<AuthorSummary, Long> {
}
