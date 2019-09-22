package com.bookstore.repository;

import com.bookstore.view.NameAndGenreView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameAndGenreViewRepository extends JpaRepository<NameAndGenreView, Long> {
}
