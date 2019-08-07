package com.bookstore.repository;

import com.bookstore.entity.Ebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {

    @Transactional(readOnly = true)
    Ebook findByTitle(String title);
}
