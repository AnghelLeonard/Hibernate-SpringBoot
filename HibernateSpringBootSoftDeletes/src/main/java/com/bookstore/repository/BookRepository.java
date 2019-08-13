package com.bookstore.repository;

import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface BookRepository extends JpaRepository<Book, Long> {
   
    @Query(value = "SELECT * FROM book", nativeQuery = true)
    List<Book> findAllIncludingDeleted();

    @Query(value = "SELECT * FROM book AS b WHERE b.deleted = true", nativeQuery = true)
    List<Book> findAllOnlyDeleted();
    
    @Transactional
    @Query(value = "UPDATE Book b SET b.deleted = false WHERE b.author.id = ?1")
    @Modifying    
    public void restoreByAuthorId(Long id);
    
    @Transactional
    @Query(value = "UPDATE Book b SET b.deleted = false WHERE b.id = ?1")
    @Modifying    
    public void restoreById(Long id);
}
