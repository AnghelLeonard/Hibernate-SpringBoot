package com.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TennisController {

    @Autowired
    private TennisService tennisService;
   
    // Query all tournaments even if they don't have players (JPQL)
    @RequestMapping("/allTournamentsRightJoinJpql")
    public List<TournamentPlayerNameDto> allTournamentsRightJoinJpql() {

       return tennisService.fetchAllTournamentsRightJoinJpql();
    }
    
    // Query all tournaments even if they don't have players (SQL)
    @RequestMapping("/allTournamentsRightJoinSql")
    public List<TournamentPlayerNameDto> allTournamentsRightJoinSql() {

       return tennisService.fetchAllTournamentsRightJoinSql();
    }
    
    // Query all players even if they are not in tournaments (JPQL)
    @RequestMapping("/allPlayersRightJoinJpql")
    public List<TournamentPlayerNameDto> allPlayersRightJoinJpql() {

       return tennisService.fetchAllPlayersRightJoinJpql();
    }
    
    // Query all players even if they are not in tournaments (SQL)
    @RequestMapping("/allPlayersRightJoinSql")
    public List<TournamentPlayerNameDto> allPlayersRightJoinSql() {

       return tennisService.fetchAllPlayersRightJoinSql();
    }
}
