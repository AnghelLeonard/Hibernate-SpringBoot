package com.bookstore.repository;

import com.bookstore.entity.Paperback;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperbackRepository extends BookBaseRepository<Paperback> {
}
