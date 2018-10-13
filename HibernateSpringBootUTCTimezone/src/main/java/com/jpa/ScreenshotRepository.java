package com.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenshotRepository extends JpaRepository<Screenshot, Long> {
    
    public Screenshot findByName(String name);
}
