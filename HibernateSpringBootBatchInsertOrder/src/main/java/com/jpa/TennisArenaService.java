package com.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class TennisArenaService {

    private final Dao dao;

    public TennisArenaService(Dao dao) {
        this.dao = dao;
    }

    private static final Logger logger
            = Logger.getLogger(TennisArenaService.class.getName());

    public void insertPlayers() {

        Random rnd = new Random();
        List<Tournament> tournaments = new ArrayList<>();

        long pk = -1;
        for (long i = 0; i < 50; i++) {

            Tournament tournament = new Tournament();
            tournament.setId(i);
            tournament.setName("Tournament: " + i);

            for (long j = 0; j < rnd.nextInt(5); j++) {
                TennisPlayer player = new TennisPlayer();
                player.setId(++pk);
                player.setName("Player: " + i);

                tournament.addTennisPlayer(player);
            }

            tournaments.add(tournament);
        }

        dao.saveInBatch(tournaments);
    }
}
