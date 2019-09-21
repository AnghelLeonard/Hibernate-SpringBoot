package com.bookstore.repository;

import com.bookstore.view.AuthorGenrePromoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorGenrePromoViewRepository extends JpaRepository<AuthorGenrePromoView, Long> {
}
