package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional(readOnly = true)
    Optional<Author> findByName(String name);

    /*
    @Query("SELECT a FROM Author a WHERE a.name=?1")
    Optional<Author> fetchByName(String name);
    
    @Query("SELECT a.genre FROM Author a WHERE a.name=?1")
    Optional<String> fetchGenreByName(String name);
    
    @Query(value="SELECT a.genre FROM author a WHERE a.name=?1", nativeQuery=true)
    Optional<String> fetchGenreByNameNative(String name);
     */
}
