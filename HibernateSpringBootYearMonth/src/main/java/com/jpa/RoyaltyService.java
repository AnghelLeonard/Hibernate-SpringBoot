package com.jpa;

import java.time.YearMonth;
import org.springframework.stereotype.Service;

@Service
public class RoyaltyService {

    private final RoyaltyRepository royaltyRepository;

    public RoyaltyService(RoyaltyRepository royaltyRepository) {
        this.royaltyRepository = royaltyRepository;
    }

    public void newRoyalty() {

        Royalty royalty = new Royalty();

        royalty.setAmount(45.5f);
        royalty.setPayedOn(YearMonth.now());

        royaltyRepository.save(royalty);

    }
}
