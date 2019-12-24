package com.bookstore.repository;

import com.bookstore.entity.Ebook;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {

    @Transactional(readOnly = true)
    Ebook findByTitle(String title);
    
    @Transactional(readOnly = true)
    @Query("SELECT e FROM Ebook e JOIN FETCH e.author")
    Ebook fetchByAuthorId(Long id);
}
