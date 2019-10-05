package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface AuhorRepository extends JpaRepository<Author, Long> {

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)    
    @Override
    public Optional<Author> findById(Long id);        
}
