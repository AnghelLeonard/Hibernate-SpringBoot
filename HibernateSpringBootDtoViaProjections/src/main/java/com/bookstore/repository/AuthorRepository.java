package com.bookstore.repository;

import java.util.List;
import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.projection.AuthorNameAge;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {      
    
    @Transactional(readOnly = true)
    List<AuthorNameAge> findFirst2ByGenre(String genre);
}

