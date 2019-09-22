package com.bookstore.repository;

import com.bookstore.view.AuthorAnthologyView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorAnthologyViewRepository extends JpaRepository<AuthorAnthologyView, Long> {     
}
