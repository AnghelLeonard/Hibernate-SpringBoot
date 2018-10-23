package com.jpa;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class TeamCup extends TennisTournament implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String teamMembers;
    private int noOfTeams;

    public String getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(String teamMembers) {
        this.teamMembers = teamMembers;
    }

    public int getNoOfTeams() {
        return noOfTeams;
    }

    public void setNoOfTeams(int noOfTeams) {
        this.noOfTeams = noOfTeams;
    }       
}
