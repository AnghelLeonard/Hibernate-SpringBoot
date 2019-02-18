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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql
        = "UPDATE tournament "
        + "SET deleted = true "
        + "WHERE id = ?")
@Where(clause = "deleted = false")
public class Tournament implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deleted")
    private boolean deleted;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "tournament", orphanRemoval = true)
    private List<TennisPlayer> tennisPlayers = new ArrayList<>();

    public void addTennisPlayer(TennisPlayer tennisPlayer) {
        this.tennisPlayers.add(tennisPlayer);
        tennisPlayer.setTournament(this);
    }

    public void removeTennisPlayer(TennisPlayer tennisPlayer) {
        tennisPlayer.setTournament(null);
        this.tennisPlayers.remove(tennisPlayer);
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

    public List<TennisPlayer> getTennisPlayers() {
        return tennisPlayers;
    }

    public void setTennisPlayers(List<TennisPlayer> tennisPlayers) {
        this.tennisPlayers = tennisPlayers;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
