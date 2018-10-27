package com.jpa;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TennisController {

    @Autowired
    private TennisService tennisService;

    // Open Session In View will "force" lazy initialization of players
    @RequestMapping("/tournament_and_players")
    public Tournament tournamentWithPlayersLazyInitialized() {

        Tournament tournament = tennisService.fetchTournament();       

        return tournament;
    }
    
    // Open Session In View will NOT "force" lazy initialization of players
    @RequestMapping("/tournament_no_players")
    public Tournament tournamentWithoutPlayers() {

        Tournament tournament = tennisService.fetchTournament();
        // explicitly set Players of the detached Tournament
        // in order to avoid fetching them from the database
        tournament.setTennisPlayers(Collections.emptyList());

        return tournament;
    }
}
