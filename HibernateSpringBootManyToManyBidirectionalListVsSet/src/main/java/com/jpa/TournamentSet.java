package com.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
@Table(name = "tournament_set")
public class TournamentSet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tournament_set_player",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
    )
    private Set<PlayerSet> players = new HashSet<>();

    public void addPlayer(PlayerSet player) {
        players.add(player);
        player.getTournaments().add(this);
    }

    public void removePlayer(PlayerSet player) {
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

    public Set<PlayerSet> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerSet> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TournamentSet)) {
            return false;
        }

        return id != null && id.equals(((TournamentSet) obj).id);
    }

    @Override
    public int hashCode() {
        return 2018;
    }
}
