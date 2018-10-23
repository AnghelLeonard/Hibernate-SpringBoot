package com.jpa;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Master extends TennisTournament implements Serializable {

    private static final long serialVersionUID = 1L;

    private String masterAtp;
    private String preGrandSlam;
    private int noOfPlayers;

    public String getMasterAtp() {
        return masterAtp;
    }

    public void setMasterAtp(String masterAtp) {
        this.masterAtp = masterAtp;
    }

    public String getPreGrandSlam() {
        return preGrandSlam;
    }

    public void setPreGrandSlam(String preGrandSlam) {
        this.preGrandSlam = preGrandSlam;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    public void setNoOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }           
}
