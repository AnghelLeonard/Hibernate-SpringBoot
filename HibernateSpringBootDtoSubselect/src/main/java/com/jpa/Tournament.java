package com.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tournaments")
public class Tournament implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tournament_name")
    private String tname;
    
    @Column(name = "tournament_town")
    private String ttown;
    
    @Column(name = "tournament_country")
    private String tcountry;
    
    @Column(name = "tournament_court")
    private String tcourt;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "tournament", orphanRemoval = true)
    private List<Player> players = new ArrayList<Player>();

    public void addPlayer(Player player) {
        players.add(player);
        player.setTournament(this);
    }

    public void removePlayer(Player player) {
        player.setTournament(null);
        players.remove(player);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getTtown() {
        return ttown;
    }

    public void setTtown(String ttown) {
        this.ttown = ttown;
    }

    public String getTcountry() {
        return tcountry;
    }        

    public String getTcourt() {
        return tcourt;
    }

    public void setTcourt(String tcourt) {
        this.tcourt = tcourt;
    }
       
}