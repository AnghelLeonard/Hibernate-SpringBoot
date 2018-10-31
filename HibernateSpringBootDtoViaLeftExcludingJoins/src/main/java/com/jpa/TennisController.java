package com.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TennisController {

    @Autowired
    private TennisService tennisService;
   
    // Query all players that are not in tournaments (JPQL)
    @RequestMapping("/allPlayersLeftExcludingJoinJpql")
    public List<TournamentPlayerNameDto> allPlayersLeftExcludingJoinJpql() {

       return tennisService.fetchAllPlayersLeftExcludingJoinJpql();
    }
    
    // Query all players that are not in tournaments (SQL)
    @RequestMapping("/allPlayersLeftExcludingJoinSql")
    public List<TournamentPlayerNameDto> allPlayersLeftExcludingJoinSql() {

       return tennisService.fetchAllPlayersLeftExcludingJoinSql();
    }
    
    // Query all tournaments that don't have players (JPQL)
    @RequestMapping("/allTournamentsLeftExcludingJoinJpql")
    public List<TournamentPlayerNameDto> allTournamentsLeftExcludingJoinJpql() {

       return tennisService.fetchAllTournamentsLeftExcludingJoinJpql();
    }
    
    // Query all tournaments that don't have players (SQL)
    @RequestMapping("/allTournamentsLeftExcludingJoinSql")
    public List<TournamentPlayerNameDto> allTournamentsLeftExcludingJoinSql() {

       return tennisService.fetchAllTournamentsLeftExcludingJoinSql();
    }
}
