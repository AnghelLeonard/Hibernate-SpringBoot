package com.app.repository;

import com.app.entity.UserGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGoodRepository extends JpaRepository<UserGood, Long> {        
}

