package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT a FROM Author a WHERE a.id IN "
            + "(SELECT b.authorId FROM Bestseller b)")
    public List<Author> fetchTheBest();

    // this is faster
    /*
    @Transactional(readOnly = true)
    @Query(value = "SELECT a FROM Author a "
            + "INNER JOIN Bestseller b ON a.id = b.authorId")
    public List<Author> fetchTheBest();
     */
}
