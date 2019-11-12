package com.bookstore.repository;

import com.bookstore.dto.AuthorBookDto;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT a.name AS name, a.age AS age, b.title AS title, b.isbn AS isbn"
            + " FROM Author a LEFT JOIN a.books b WHERE a.genre = ?1")
    Page<AuthorBookDto> fetchPageOfDto(String genre, Pageable pageable);
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT a.name AS name, a.age AS age, b.title AS title, b.isbn AS isbn"
            + " FROM Author a LEFT JOIN a.books b WHERE a.genre = ?1")
    Slice<AuthorBookDto> fetchSliceOfDto(String genre, Pageable pageable);
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT a.name AS name, a.age AS age, b.title AS title, b.isbn AS isbn"
            + " FROM Author a LEFT JOIN a.books b WHERE a.genre = ?1")
    List<AuthorBookDto> fetchListOfDto(String genre, Pageable pageable);

    @Transactional(readOnly = true)
    @Query(value = "SELECT a.name AS name, a.age AS age, b.title AS title, b.isbn AS isbn,"
            + " COUNT(*) OVER() AS total FROM author a LEFT JOIN book b "
            + "ON a.id = b.author_id WHERE a.genre = ?1",
            nativeQuery = true)
    List<AuthorBookDto> fetchListOfDtoNative(String genre, Pageable pageable);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM (SELECT *, DENSE_RANK() OVER (ORDER BY name, age) na_rank "
            + "FROM (SELECT a.name AS name, a.age AS age, b.title AS title, b.isbn AS isbn"
            + " FROM author a LEFT JOIN book b ON a.id = b.author_id WHERE a.genre = ?1 "
            + "ORDER BY a.name) ab ) ab_r WHERE ab_r.na_rank > ?2 AND ab_r.na_rank <= ?3",
            nativeQuery = true)
    List<AuthorBookDto> fetchListOfDtoNativeDenseRank(String genre, int start, int end);    
}
