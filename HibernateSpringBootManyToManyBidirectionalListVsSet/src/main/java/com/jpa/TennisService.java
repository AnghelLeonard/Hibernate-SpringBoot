package com.jpa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TennisService {

    private final TournamentSetRepository tournamentSetRepository;
    private final TournamentListRepository tournamentListRepository;

    public TennisService(TournamentSetRepository tournamentRepository,
            TournamentListRepository tournamentListRepository) {

        this.tournamentSetRepository = tournamentRepository;
        this.tournamentListRepository = tournamentListRepository;
    }

    @Transactional
    public void addTournamentsAndPlayersRemoveOnePlayerList() {
        
        TournamentList t1List = new TournamentList();
        t1List.setName("Wimbledon");
        TournamentList t2List = new TournamentList();
        t2List.setName("Roland Garros");
        
        PlayerList p1List = new PlayerList();
        p1List.setName("Roger Federer");
        PlayerList p2List = new PlayerList();
        p2List.setName("Rafael Nadal");
        PlayerList p3List = new PlayerList();
        p3List.setName("David Ferer");
        
        t1List.addPlayer(p1List);
        t1List.addPlayer(p2List);
        t2List.addPlayer(p1List);
        t2List.addPlayer(p2List);
        t2List.addPlayer(p3List);
       
        tournamentListRepository.save(t1List);
        tournamentListRepository.saveAndFlush(t2List);
        
        System.out.println("================================================");
        System.out.println("Removing a player (list) ...");
        System.out.println("================================================");
        
        t2List.removePlayer(p2List);
    }    
    
    @Transactional
    public void addTournamentsAndPlayersRemoveOnePlayerSet() {

        TournamentSet t1Set = new TournamentSet();
        t1Set.setName("Wimbledon");
        TournamentSet t2Set = new TournamentSet();
        t2Set.setName("Roland Garros");
                
        PlayerSet p1Set = new PlayerSet();
        p1Set.setName("Roger Federer");
        PlayerSet p2Set = new PlayerSet();
        p2Set.setName("Rafael Nadal");
        PlayerSet p3Set = new PlayerSet();
        p3Set.setName("David Ferer");

        t1Set.addPlayer(p1Set);
        t1Set.addPlayer(p2Set);
        t2Set.addPlayer(p1Set);
        t2Set.addPlayer(p2Set);
        t2Set.addPlayer(p3Set);

        tournamentSetRepository.save(t1Set);
        tournamentSetRepository.saveAndFlush(t2Set);
        
        System.out.println("================================================");
        System.out.println("Removing a player (Set) ...");
        System.out.println("================================================");
        
        t2Set.removePlayer(p2Set);   
    }
}

