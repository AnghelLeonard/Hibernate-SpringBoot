package com.bookstore.repository;

import com.bookstore.entity.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookReviewRepository extends JpaRepository<BookReview, Long> {
    
    @Transactional(readOnly=true)
    BookReview findByEmail(String email);
}
