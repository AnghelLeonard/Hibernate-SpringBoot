package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends SoftDeleteRepository<Author, Long> {  

    @Query("SELECT a FROM Author a WHERE a.name=?1 AND a.deleted=false")
    Author fetchByName(String name);
}
