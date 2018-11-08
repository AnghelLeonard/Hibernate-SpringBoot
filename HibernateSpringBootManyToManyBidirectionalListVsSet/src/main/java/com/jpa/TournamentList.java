package com.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tournament_list")
public class TournamentList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tournament_list_player",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private List<PlayerList> players = new ArrayList<>();

    public void addPlayer(PlayerList player) {
        players.add(player);
        player.getTournaments().add(this);
    }

    public void removePlayer(PlayerList player) {
        players.remove(player);
        player.getTournaments().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerList> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerList> players) {
        this.players = players;
    }    

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TournamentList)) {
            return false;
        }

        return id != null && id.equals(((TournamentList) obj).id);
    }

    @Override
    public int hashCode() {
        return 2018;
    }
}
