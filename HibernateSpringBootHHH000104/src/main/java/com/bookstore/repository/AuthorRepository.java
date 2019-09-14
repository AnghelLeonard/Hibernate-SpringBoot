package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import javax.persistence.QueryHint;
import static org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.dto.AuthorBookDto;
import org.springframework.data.domain.Slice;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // this query lead to 
    // HHH000104: firstResult/maxResults specified with collection fetch; applying in memory!
    /*
    @Transactional
    @Query(value = "SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.genre = ?1",
            countQuery = "SELECT COUNT(a) FROM Author a WHERE a.genre = ?1")
    Page<Author> fetchWithBooksByGenre(String genre, Pageable pageable);        
     */
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT a.id FROM Author a WHERE a.genre = ?1")
    Page<Long> fetchPageOfIdsByGenre(String genre, Pageable pageable);
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT a.id FROM Author a WHERE a.genre = ?1")
    Slice<Long> fetchSliceOfIdsByGenre(String genre, Pageable pageable);

    @Transactional
    @QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false"))
    @Query(value = "SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books WHERE a.id IN ?1")
    List<Author> fetchWithBooks(List<Long> authorIds);

    @Transactional(readOnly = true)
    @Query(value = "SELECT a.name AS name, a.age AS age, b.title AS title, b.isbn AS isbn"
            + " FROM Author a LEFT JOIN a.books b WHERE a.genre = ?1")
    Page<AuthorBookDto> fetchPageOfDtoWithBooksDto(String genre, Pageable pageable);
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT a.name AS name, a.age AS age, b.title AS title, b.isbn AS isbn"
            + " FROM Author a LEFT JOIN a.books b WHERE a.genre = ?1")
    Slice<AuthorBookDto> fetchSliceOfDtoWithBooksDto(String genre, Pageable pageable);

    @Transactional(readOnly = true)
    @Query(value = "SELECT a.name AS name, a.age AS age, b.title AS title, b.isbn AS isbn,"
            + " COUNT(*) OVER() AS total FROM author a LEFT JOIN book b "
            + "ON a.id = b.author_id WHERE a.genre = ?1",
            nativeQuery = true)
    List<AuthorBookDto> fetchNativeDtoWithBooksDto(String genre, Pageable pageable);

    @Transactional(readOnly = true)
    @Query(value = "SELECT * FROM (SELECT *, DENSE_RANK() OVER (ORDER BY name, age) na_rank "
            + "FROM (SELECT a.name AS name, a.age AS age, b.title AS title, b.isbn AS isbn"
            + " FROM author a LEFT JOIN book b ON a.id = b.author_id WHERE a.genre = ?1 "
            + "ORDER BY a.name) ab ) ab_r WHERE ab_r.na_rank > ?2 AND ab_r.na_rank <= ?3",
            nativeQuery = true)
    List<AuthorBookDto> fetchNativeDtoWithBooksDtoViaDenseRank(String genre, int start, int end);
}
