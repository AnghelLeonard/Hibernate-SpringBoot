package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE a.genre = ?1")
    public Page<Author> fetchByGenre(String genre, Pageable pageable);
    
    @Query(value = "SELECT a FROM Author a WHERE a.genre = ?1",
            countQuery = "SELECT COUNT(*) FROM Author a WHERE a.genre = ?1")
    public Page<Author> fetchByGenreExplicitCount(String genre, Pageable pageable);

    @Query(value = "SELECT * FROM author WHERE genre = ?1", 
            nativeQuery = true)
    public Page<Author> fetchByGenreNative(String genre, Pageable pageable);
    
    @Query(value = "SELECT * FROM author WHERE genre = ?1", 
            countQuery = "SELECT COUNT(*) FROM author WHERE genre = ?1",
            nativeQuery = true)
    public Page<Author> fetchByGenreNativeExplicitCount(String genre, Pageable pageable);
}
