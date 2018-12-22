package com.jpa;

import java.time.Instant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoyaltyRepository extends JpaRepository<Royalty, Long> {

    @Query(value = "select concat_ws(r.name, ?1, r.amount, ?2) from Royalty r WHERE r.id = 1")
    String fetchNameAndAmount(String symbol, Instant instant);
}
