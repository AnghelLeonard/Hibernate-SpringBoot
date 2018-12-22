package com.jpa;

import java.time.Instant;
import org.springframework.stereotype.Service;

@Service
public class RoyaltyService {

    private final Dao dao;
    private final RoyaltyRepository royaltyRepository;

    public RoyaltyService(Dao dao, RoyaltyRepository royaltyRepository) {
        this.dao = dao;
        this.royaltyRepository = royaltyRepository;
    }

    public String nameAndAmount() {
        return royaltyRepository.fetchNameAndAmount("$", Instant.now());
        // or, via EntityManager from Dao class
        //return dao.fetchNameAndAmount("$", Instant.now());
    }
}
