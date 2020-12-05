package com.bookstore.repository;

import com.bookstore.entity.Book;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Book> findById(Long id);        
}
