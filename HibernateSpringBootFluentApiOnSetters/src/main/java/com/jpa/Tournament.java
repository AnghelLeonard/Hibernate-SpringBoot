package com.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tournament implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, 
            mappedBy = "tournament", orphanRemoval = true)
    private List<TennisPlayer> tennisPlayers = new ArrayList<>();

    public Tournament addTennisPlayer(TennisPlayer tennisPlayer) {
        this.tennisPlayers.add(tennisPlayer);
        tennisPlayer.setTournament(this);
        return this;
    }

    public Tournament removeTennisPlayer(TennisPlayer tennisPlayer) {
        tennisPlayer.setTournament(null);
        this.tennisPlayers.remove(tennisPlayer);
        return this;
    }

    public Long getId() {
        return id;
    }

    public Tournament setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tournament setName(String name) {
        this.name = name;
        return this;
    }

    public List<TennisPlayer> getTennisPlayers() {
        return tennisPlayers;
    }

    public Tournament setTennisPlayers(List<TennisPlayer> tennisPlayers) {
        this.tennisPlayers = tennisPlayers;
        return this;
    }
}
