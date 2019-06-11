package com.bookstore.repository;

import java.util.List;
import javax.persistence.QueryHint;
import com.bookstore.entity.Author;
import static org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // INNER JOIN
    @Query(value = "SELECT DISTINCT a FROM Author a INNER JOIN a.books b WHERE b.price > ?1")
    @QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false"))
    List<Author> fetchAuthorsBooksByPriceInnerJoin(int price);

    // JOIN FETCH
    @Query(value = "SELECT DISTINCT a FROM Author a JOIN FETCH a.books b WHERE b.price > ?1")
    @QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false"))
    List<Author> fetchAuthorsBooksByPriceJoinFetch(int price);
}
