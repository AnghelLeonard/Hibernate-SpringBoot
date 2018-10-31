package com.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TennisController {

    @Autowired
    private TennisService tennisService;
   
    // Query all tournaments that don't have players 
    // and all players that don't participate in tournaments (JPQL)
    @RequestMapping("/allTournamentsWithoutPlayersAndViceversaOuterExcludingJoinJpql")
    public List<TournamentPlayerNameDto> allTournamentsWithoutPlayersAndViceversaOuterExcludingJoinJpql() {

       return tennisService.fetchAllTournamentsWithoutPlayersAndViceversaOuterExcludingJoinJpql();
    }
   
    // Query all tournaments that don't have players 
    // and all players that don't participate in tournaments (SQL)
    @RequestMapping("/allTournamentsWithoutPlayersAndViceversaOuterExcludingJoinSql")
    public List<TournamentPlayerNameDto> allTournamentsWithoutPlayersAndViceversaOuterExcludingJoinSql() {

       return tennisService.fetchAllTournamentsWithoutPlayersAndViceversaOuterExcludingJoinSql();
    }
    
    // Query all players that don't participate in tournaments
    // and all tournaments that don't have players (JPQL)
    @RequestMapping("/allPlayersWithoutTournamentsAndViceversaOuterExcludingJoinJpql")
    public List<TournamentPlayerNameDto> allPlayersWithoutTournamentsAndViceversaOuterExcludingJoinJpql() {

       return tennisService.fetchAllPlayersWithoutTournamentsAndViceversaOuterExcludingJoinJpql();
    }
    
    // Query all players that don't participate in tournaments
    // and all tournaments that don't have players (SQL)
    @RequestMapping("/allPlayersWithoutTournamentsAndViceversaOuterExcludingJoinSql")
    public List<TournamentPlayerNameDto> allPlayersWithoutTournamentsAndViceversaOuterExcludingJoinSql() {

       return tennisService.fetchAllPlayersWithoutTournamentsAndViceversaOuterExcludingJoinSql();
    }
}
