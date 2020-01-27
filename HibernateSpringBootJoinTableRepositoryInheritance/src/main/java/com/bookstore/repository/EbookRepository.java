package com.bookstore.repository;

import com.bookstore.entity.Ebook;
import org.springframework.stereotype.Repository;

@Repository
public interface EbookRepository extends BookBaseRepository<Ebook> {
}
