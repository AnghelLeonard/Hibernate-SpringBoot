package com.bookstore.repository;

import com.bookstore.entity.Author;
import com.bookstore.interfacebased.dto.AuthorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.interfacebased.dto.MirrorAuthorDto;
import java.util.List;
import javax.persistence.QueryHint;
import static org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH;
import org.springframework.data.jpa.repository.QueryHints;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT DISTINCT a FROM Author a JOIN FETCH a.books")
    @QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH,
            value = "false"))
    List<Author> fetchReadOnlyEntities();

    @Query(value = "SELECT a FROM Author a JOIN FETCH a.books")
    List<MirrorAuthorDto> fetchMirrorInterface();

    @Query(value = "SELECT a FROM Author a JOIN FETCH a.books")
    List<AuthorDto> fetchDtoInterface();
}
