package com.bookstore.repository;

import com.bookstore.entity.Paperback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaperbackRepository extends JpaRepository<Paperback, Long> {

    @Transactional(readOnly = true)
    Paperback findByTitle(String title);
}
