package com.bookstore.repository;

import com.bookstore.entity.Book;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Book> findById(Long id);    
    
    @Modifying
    @Query("UPDATE Book SET title = ?1 WHERE id = ?2")
    public void updateTitle(String title, long id);        
}
