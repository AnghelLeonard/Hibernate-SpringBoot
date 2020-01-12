package com.bookstore.repository;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public interface AuthorRepository extends JpaRepository<Author, Long> {
            
    Author findByBooks(Book book);
    
    Author findByBooksReviews(Review review);
    
    AuthorDto queryByBooks(Book book);
    
    AuthorDto queryByBooksReviews(Review review);
}
