package com.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TennisController {

    @Autowired
    private TennisService tennisService;
   
    // Query all tournaments that don't have players (JPQL)
    @RequestMapping("/allTournamentsRightExcludingJoinJpql")
    public List<TournamentPlayerNameDto> allTournamentsRightExcludingJoinJpql() {

       return tennisService.fetchAllTournamentsRightExcludingJoinJpql();
    }
    
    // Query all tournaments that don't have players (SQL)
    @RequestMapping("/allTournamentsRightExcludingJoinSql")
    public List<TournamentPlayerNameDto> allTournamentsRightExcludingJoinSql() {

       return tennisService.fetchAllTournamentsRightExcludingJoinSql();
    }
    
    // Query all players that are not in tournaments (JPQL)
    @RequestMapping("/allPlayersRightExcludingJoinJpql")
    public List<TournamentPlayerNameDto> allPlayersRightExcludingJoinJpql() {

       return tennisService.fetchAllPlayersRightExcludingJoinJpql();
    }
    
    // Query all players that are not in tournaments (SQL)
    @RequestMapping("/allPlayersRightExcludingJoinSql")
    public List<TournamentPlayerNameDto> allPlayersRightExcludingJoinSql() {

       return tennisService.fetchAllPlayersRightExcludingJoinSql();
    }
}
