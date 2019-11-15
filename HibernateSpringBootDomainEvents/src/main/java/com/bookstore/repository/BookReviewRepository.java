package com.bookstore.repository;

import com.bookstore.entity.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReview, Long> {
    
    BookReview findByEmail(String email);
}
