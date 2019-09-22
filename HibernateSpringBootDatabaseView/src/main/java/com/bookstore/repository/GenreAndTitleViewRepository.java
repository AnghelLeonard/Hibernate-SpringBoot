package com.bookstore.repository;

import com.bookstore.view.GenreAndTitleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreAndTitleViewRepository extends JpaRepository<GenreAndTitleView, Long> {
}
