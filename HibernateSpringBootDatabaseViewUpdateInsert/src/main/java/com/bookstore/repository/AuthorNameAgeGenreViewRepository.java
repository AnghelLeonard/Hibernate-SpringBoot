package com.bookstore.repository;

import com.bookstore.view.AuthorNameAgeGenreView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorNameAgeGenreViewRepository extends JpaRepository<AuthorNameAgeGenreView, Long> {
}
