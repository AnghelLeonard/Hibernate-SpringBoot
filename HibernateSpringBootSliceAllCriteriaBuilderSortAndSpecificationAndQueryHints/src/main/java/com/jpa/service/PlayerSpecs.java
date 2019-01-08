package com.jpa.service;

import com.jpa.domain.Player;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecs {
    
    private static final int AGE = 25;

    public static Specification<Player> isAgeGt21() {
        return (Root<Player> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> 
                builder.greaterThan(root.get("age"), AGE);
    }
}
