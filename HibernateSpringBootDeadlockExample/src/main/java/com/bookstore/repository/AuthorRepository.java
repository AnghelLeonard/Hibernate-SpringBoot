package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Author> findById(Long id);    
    
    @Modifying
    @Query("UPDATE Author SET genre = ?1 WHERE id = ?2")
    public void updateGenre(String genre, long id);        
}
