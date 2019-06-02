package com.tennis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Where;

@Entity
public class Tournament implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "tournament", orphanRemoval = true)
    @Where(clause = "atprank <= 10")
    private List<TennisPlayer> tennisPlayersTop10 = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "tournament", orphanRemoval = true)
    @Where(clause = "atprank > 10")
    private List<TennisPlayer> tennisPlayersRestOf = new ArrayList<>();    

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

    public List<TennisPlayer> getTennisPlayersTop10() {
        return tennisPlayersTop10;
    }

    public void setTennisPlayersTop10(List<TennisPlayer> tennisPlayersTop10) {
        this.tennisPlayersTop10 = tennisPlayersTop10;
    }

    public List<TennisPlayer> getTennisPlayersRestOf() {
        return tennisPlayersRestOf;
    }

    public void setTennisPlayersRestOf(List<TennisPlayer> tennisPlayersRestOf) {
        this.tennisPlayersRestOf = tennisPlayersRestOf;
    }
    
}
