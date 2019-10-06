package com.bookstore.repository;

import com.bookstore.entity.Chapter;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    @Override
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)        
    public Optional<Chapter> findById(Long id);        
}
