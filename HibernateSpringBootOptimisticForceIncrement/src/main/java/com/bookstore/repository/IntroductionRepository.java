package com.bookstore.repository;

import com.bookstore.entity.Introduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntroductionRepository extends JpaRepository<Introduction, Long> {
}
