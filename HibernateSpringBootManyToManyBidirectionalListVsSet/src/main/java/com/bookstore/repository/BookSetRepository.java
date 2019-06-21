package com.bookstore.repository;

import com.bookstore.entity.BookSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSetRepository extends JpaRepository<BookSet, Long> {
}
