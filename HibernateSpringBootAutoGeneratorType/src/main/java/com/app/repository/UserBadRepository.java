package com.app.repository;

import com.app.entity.UserBad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBadRepository extends JpaRepository<UserBad, Long> {        
}

