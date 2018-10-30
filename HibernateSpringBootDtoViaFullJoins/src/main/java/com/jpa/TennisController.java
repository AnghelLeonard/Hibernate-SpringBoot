package com.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TennisController {

    @Autowired
    private TennisService tennisService;
   
    // Query all players and tournaments (JPQL)
    @RequestMapping("/allPlayersAndTournamentsFullJoinJpql")
    public List<TournamentPlayerNameDto> allPlayersAndTournamentsFullJoinJpql() {

       return tennisService.fetchAllPlayersAndTournamentsFullJoinJpql();
    }
    
    // Query all players and tournaments (SQL)
    @RequestMapping("/allPlayersAndTournamentsFullJoinSql")
    public List<TournamentPlayerNameDto> allPlayersAndTournamentsFullJoinSql() {

       return tennisService.fetchAllPlayersAndTournamentsFullJoinSql();
    }
    
    // Query all tournaments and players (JPQL)
    @RequestMapping("/allTournamentsAndPlayersFullJoinJpql")
    public List<TournamentPlayerNameDto> allTournamentsAndPlayersFullJoinJpql() {

       return tennisService.fetchAllTournamentsAndPlayersFullJoinJpql();
    }
    
    // Query all tournaments and players (SQL)
    @RequestMapping("/allTournamentsAndPlayersFullJoinSql")
    public List<TournamentPlayerNameDto> allTournamentsAndPlayersFullJoinSql() {

       return tennisService.fetchAllTournamentsAndPlayersFullJoinSql();
    }
}
