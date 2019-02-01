package com.sample.boundary;

import com.sample.model.Player;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("player")
@Stateless
public class PlayerResource {

    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;

    @POST
    public String generatePlayer() {

        Player player = new Player();
        player.setName("Some Name");
        player.setCity("Some city");
        player.setAge(20);

        em.persist(player);       

        return "Success";
    }
}
