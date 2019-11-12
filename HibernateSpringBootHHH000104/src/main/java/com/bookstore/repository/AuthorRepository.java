package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import javax.persistence.QueryHint;
import javax.persistence.Tuple;
import static org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;

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
    @Query(value = "SELECT a.id AS id, COUNT(*) OVER() AS total FROM Author a WHERE a.genre = ?1",
            nativeQuery = true)
    List<Tuple> fetchTupleOfIdsByGenre(String genre, Pageable pageable);

    @Transactional(readOnly = true)
    @Query(value = "SELECT a.id FROM Author a WHERE a.genre = ?1")
    Slice<Long> fetchSliceOfIdsByGenre(String genre, Pageable pageable);

    @Transactional(readOnly = true)
    @Query(value = "SELECT a.id FROM Author a WHERE a.genre = ?1")
    List<Long> fetchListOfIdsByGenre(String genre, Pageable pageable);

    @Transactional(readOnly = true)
    @QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false"))
    @Query(value = "SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books WHERE a.id IN ?1")
    List<Author> fetchWithBooksJoinFetch(List<Long> authorIds);

    @Transactional(readOnly = true)
    @EntityGraph(attributePaths = {"books"}, type = EntityGraph.EntityGraphType.FETCH)
    @QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false"))
    @Query(value = "SELECT DISTINCT a FROM Author a WHERE a.id IN ?1")
    List<Author> fetchWithBooksEntityGraph(List<Long> authorIds);
}
