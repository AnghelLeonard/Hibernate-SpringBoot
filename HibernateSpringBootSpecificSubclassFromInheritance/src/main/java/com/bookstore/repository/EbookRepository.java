package com.bookstore.repository;

import com.bookstore.entity.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT b FROM Author a JOIN a.books b WHERE a.name = ?1 AND TYPE(b) = 'Ebook'")
    public Ebook findByAuthorName(String name);
}
