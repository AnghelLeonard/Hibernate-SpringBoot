package com.bookstore.repository;

import com.bookstore.entity.AuthorModification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorModificationRepository extends JpaRepository<AuthorModification, Long> {
}
