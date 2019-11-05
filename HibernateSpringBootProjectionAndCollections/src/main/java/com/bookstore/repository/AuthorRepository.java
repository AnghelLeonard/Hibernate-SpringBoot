package com.bookstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookstore.dto.AuthorDto;
import com.bookstore.dto.SimpleAuthorDto;
import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<AuthorDto> findBy();
  
    @Query("SELECT a.name AS name, a.genre AS genre, b AS books "
            + "FROM Author a INNER JOIN a.books b")
    List<AuthorDto> findByViaQuery();
   
    @Query("SELECT a.name AS name, a.genre AS genre, b.title AS title "
            + "FROM Author a INNER JOIN a.books b")
    List<SimpleAuthorDto> findByViaQuerySimpleDto();
}
