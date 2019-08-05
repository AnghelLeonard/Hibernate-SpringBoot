package com.bookstore.repository;

import java.util.List;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookStatus;
import org.hibernate.LockOptions;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
        @QueryHint(name = "javax.persistence.lock.timeout", value = "" + LockOptions.SKIP_LOCKED)})
    public List<Book> findTop3ByStatus(BookStatus status, Sort sort);
}
