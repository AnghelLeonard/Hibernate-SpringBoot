package com.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ScreenshotRepository extends JpaRepository<Screenshot, Long> {
    
    @Transactional(readOnly=true)
    public Screenshot findByName(String name);
}
