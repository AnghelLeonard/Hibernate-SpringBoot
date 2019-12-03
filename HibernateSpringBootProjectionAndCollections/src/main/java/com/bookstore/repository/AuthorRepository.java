package com.bookstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.spring.dto.AuthorDto;
import com.bookstore.spring.dto.SimpleAuthorDto;
import com.bookstore.entity.Author;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<AuthorDto> findBy();

    @Query("SELECT a.name AS name, a.genre AS genre, b AS books "
            + "FROM Author a INNER JOIN a.books b")
    List<AuthorDto> findByViaQuery();

    @Query("SELECT a FROM Author a JOIN FETCH a.books")
    Set<AuthorDto> findByJoinFetch();

    @Query("SELECT a.name AS name, a.genre AS genre, b.title AS title "
            + "FROM Author a INNER JOIN a.books b")
    List<SimpleAuthorDto> findByViaQuerySimpleDto();
    
    @Query("SELECT a.name AS name, a.genre AS genre, b.title AS title "
            + "FROM Author a INNER JOIN a.books b")
    List<Object[]> findByViaArrayOfObjects();
    
    @Query("SELECT a.id AS authorId, a.name AS name, a.genre AS genre, "
            + "b.id AS bookId, b.title AS title FROM Author a INNER JOIN a.books b")
    List<Object[]> findByViaArrayOfObjectsWithIds();
}
