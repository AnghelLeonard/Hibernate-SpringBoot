package com.bookstore.repository;

import com.bookstore.entity.Book;
import java.time.Instant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT concat_ws(b.title, ?1, b.price, ?2) FROM Book b WHERE b.id = 1")
    String fetchTitleAndPrice(String symbol, Instant instant);
}
