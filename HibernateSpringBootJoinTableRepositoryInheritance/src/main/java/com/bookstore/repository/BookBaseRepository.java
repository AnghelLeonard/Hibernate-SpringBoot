package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
@Transactional(readOnly = true)
public interface BookBaseRepository<T extends Book> extends JpaRepository<T, Long> {

    T findByTitle(String title);
    
    @Query(value="SELECT b FROM #{#entityName} AS b WHERE b.isbn = ?1")
    T fetchByIsbn(String isbn);
}
