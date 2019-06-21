package com.bookstore.repository;

import com.bookstore.entity.AuthorList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorListRepository extends JpaRepository<AuthorList, Long> {
}
