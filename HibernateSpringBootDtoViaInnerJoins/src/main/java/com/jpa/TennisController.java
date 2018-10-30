package com.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TennisController {

    @Autowired
    private TennisService tennisService;
   
    // Query the players from all tournaments (JPQL)
    @RequestMapping("/playersFromTournamentsNamesInnerJoinJpql")
    public List<TournamentPlayerNameDto> playersFromTournamentsNamesInnerJoinJpql() {

       return tennisService.fetchPlayersFromTournamentsNamesInnerJoinJpql();
    }
    
    // Query the players from all tournaments (SQL)
    @RequestMapping("/playersFromTournamentsNamesInnerJoinSql")
    public List<TournamentPlayerNameDto> playersFromTournamentsNamesInnerJoinSql() {

       return tennisService.fetchPlayersFromTournamentsNamesInnerJoinSql();
    }
    
    // Query the tournaments of all players (JPQL)
    @RequestMapping("/tournamentsOfPlayersNamesInnerJoinJpql")
    public List<TournamentPlayerNameDto> tournamentsOfPlayersNamesInnerJoinJpql() {

       return tennisService.fetchTournamentsOfPlayersNamesInnerJoinJpql();
    }

    // Query the tournaments of all players (SQL)    
    @RequestMapping("/tournamentsOfPlayersNamesInnerJoinSql")
    public List<TournamentPlayerNameDto> tournamentsOfPlayersNamesInnerJoinSql() {

       return tennisService.fetchTournamentsOfPlayersNamesInnerJoinSql();
    }
    
    // Query all players that participate to a "tournament" (Roland Garros here)
    // and have their rank smaller or equal with "rank" (5 here) (JPQL)
    @RequestMapping("/playersRankAndNameInnerByTournamentAndRankInnerJoinJpql")
    public List<PlayerRankNameDto> playersRankAndNameInnerByTournamentAndRankInnerJoinJpql() {

       return tennisService.fetchPlayersRankAndNameInnerByTournamentAndRankInnerJoinJpql("Roland Garros", 5);
    }
    
    // Query all players that participate to a "tournament" (Roland Garros here)
    // and have their rank smaller or equal with "rank" (5 here) (SQL)
    @RequestMapping("/playersRankAndNameInnerByTournamentAndRankInnerJoinSql")
    public List<PlayerRankNameDto> playersRankAndNameInnerByTournamentAndRankInnerJoinSql() {

       return tennisService.fetchPlayersRankAndNameInnerByTournamentAndRankInnerJoinSql("Roland Garros", 5);
    }
    
    // Query all tournaments that have players with rank smaller or equal to "rank" (5 here) (JPQL)
    @RequestMapping("/tournamentsIdNameByRankInnerJoinJpql")
    public List<TournamentIdNameDto> tournamentsIdNameByRankInnerJoinJpql() {
        
        return tennisService.fetchTournamentsIdNameByRankInnerJoinJpql(5);
    }
    
    // Query all tournaments that have players with rank smaller or equal to "rank" (SQL)
    @RequestMapping("/tournamentsIdNameByRankInnerJoinSql")
    public List<TournamentIdNameDto> tournamentsIdNameByRankInnerJoinSql() {
        
        return tennisService.fetchTournamentsIdNameByRankInnerJoinSql(5);
    }
}
