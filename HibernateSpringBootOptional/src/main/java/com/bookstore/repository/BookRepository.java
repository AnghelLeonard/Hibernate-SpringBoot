package com.bookstore.repository;

import com.bookstore.entity.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional(readOnly = true)
    Optional<Book> findByTitle(String title);
}
