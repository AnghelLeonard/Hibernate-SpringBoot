package com.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TennisController {

    @Autowired
    private TennisService tennisService;
   
    // Query all players even if they are not in tournaments (JPQL)
    @RequestMapping("/allPlayersLeftJoinJpql")
    public List<TournamentPlayerNameDto> allPlayersLeftJoinJpql() {

       return tennisService.fetchAllPlayersLeftJoinJpql();
    }
    
    // Query all players even if they are not in tournaments (SQL)
    @RequestMapping("/allPlayersLeftJoinSql")
    public List<TournamentPlayerNameDto> allPlayersLeftJoinSql() {

       return tennisService.fetchAllPlayersLeftJoinSql();
    }
    
    // Query all tournaments even if they don't have players (JPQL)
    @RequestMapping("/allTournamentsLeftJoinJpql")
    public List<TournamentPlayerNameDto> allTournamentsLeftJoinJpql() {

       return tennisService.fetchAllTournamentsLeftJoinJpql();
    }
    
    // Query all tournaments even if they don't have players (SQL)
    @RequestMapping("/allTournamentsLeftJoinSql")
    public List<TournamentPlayerNameDto> allTournamentsLeftJoinSql() {

       return tennisService.fetchAllTournamentsLeftJoinSql();
    }
    
    
}
