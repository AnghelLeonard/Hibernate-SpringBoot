package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE a.id = ?1")
    public Author fetchByIdJpql(long id);
    
    @Query(value = "SELECT * FROM author WHERE id = ?1", nativeQuery = true)
    public Author fetchByIdSql(long id);

    @Query("SELECT a.name FROM Author a WHERE a.id = ?1")
    public String fetchNameByIdJpql(long id);

    @Query(value = "SELECT name FROM author WHERE id = ?1", nativeQuery = true)
    public String fetchNameByIdSql(long id);
}
