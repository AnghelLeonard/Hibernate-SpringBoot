package com.bookstore.repository;

import com.bookstore.entity.AuthorShallow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorShallowRepository extends JpaRepository<AuthorShallow, Long> {       
}

