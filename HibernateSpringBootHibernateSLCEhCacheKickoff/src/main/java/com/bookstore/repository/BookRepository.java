package com.bookstore.repository;

import com.bookstore.entity.Book;
import java.util.List;
import javax.persistence.QueryHint;
import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Transactional(readOnly = true)
    @Query("SELECT b FROM Book b WHERE b.price > ?1")
    @QueryHints(value = @QueryHint(name = HINT_CACHEABLE, value = "true"))
    public List<Book> fetchByPrice(int price);
}
