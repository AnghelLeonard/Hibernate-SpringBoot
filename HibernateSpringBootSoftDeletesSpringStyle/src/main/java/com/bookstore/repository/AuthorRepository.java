package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends SoftDeleteRepository<Author, Long> {  
    
    Author findByName(String name);
}
