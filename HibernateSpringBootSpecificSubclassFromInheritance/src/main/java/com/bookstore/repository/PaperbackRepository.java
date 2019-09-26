package com.bookstore.repository;

import com.bookstore.entity.Paperback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperbackRepository extends JpaRepository<Paperback, Long> {
}
