package com.sample.boundary;

import com.sample.model.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("player")
@Stateless
public class PlayerResource {

    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;

    private List<String> names = Arrays.asList("Mike", "John", "Virgil", "Joana", "Ike");
    private List<String> cities = Arrays.asList("Barcelona", "Ploiesti", "Madrid", "Cluj", "Bucuresti");
    private Random rnd = new Random();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Player generatePlayer() {

        Player player = new Player();
        player.setName(names.get(rnd.nextInt(names.size())));
        player.setCity(cities.get(rnd.nextInt(cities.size())));
        player.setAge(18 + rnd.nextInt(50));

        em.persist(player);

        return player;
    }
}