package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findFirstByGenre(String genre);
    
    @Query(value="SELECT tx.trx_id FROM information_schema.innodb_trx tx "
            + "WHERE tx.trx_mysql_thread_id = connection_id()", nativeQuery=true)
    public Object getTransactionId();
}
