package com.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Entity
@Subselect(
    "SELECT p.id as id, p.tournament_name as tname, p.tournament_court as tcourt FROM tournaments p")
@Synchronize({"tournaments", "players"})
@Immutable
public class TournamentDto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id    
    private Long id;

    private String tname;
    private String tcourt;
    
    @OneToMany(mappedBy = "tournament")
    private Set<Player> players = new HashSet<>();

    public Long getId() {
        return id;
    }        

    public String getTname() {
        return tname;
    }

    public String getTcourt() {
        return tcourt;
    }
        
    public Set<Player> getPlayers() {
        return players;
    }
             
}
