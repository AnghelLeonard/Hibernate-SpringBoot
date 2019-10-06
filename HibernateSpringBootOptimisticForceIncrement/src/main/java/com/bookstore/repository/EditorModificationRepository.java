package com.bookstore.repository;

import com.bookstore.entity.EditorModification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorModificationRepository extends JpaRepository<EditorModification, Long> {
}
