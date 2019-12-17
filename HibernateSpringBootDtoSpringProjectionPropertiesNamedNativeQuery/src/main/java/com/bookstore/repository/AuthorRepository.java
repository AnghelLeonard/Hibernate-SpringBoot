package com.bookstore.repository;

import java.util.List;
import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.dto.AuthorNameAge;
import org.springframework.data.jpa.repository.Query;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Scalar Mapping
    @Query(nativeQuery = true)
    List<String> fetchName();
  
    // Spring projection
    @Query(nativeQuery = true)
    List<AuthorNameAge> fetchNameAndAge();
}
