package com.sample.boundary;

import com.sample.model.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("player")
@Stateless // optional
@TransactionManagement(TransactionManagementType.BEAN)
public class PlayerResource {

    @PersistenceUnit(unitName = "MyPU")
    private EntityManagerFactory emf;

    @Resource
    private UserTransaction userTransaction;

    private List<String> names = Arrays.asList("Mike", "John", "Virgil", "Joana", "Ike");
    private List<String> cities = Arrays.asList("Barcelona", "Ploiesti", "Madrid", "Cluj", "Bucuresti");
    private Random rnd = new Random();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Player generatePlayer() throws Exception { // or use explicit exception

        Player player = new Player();
        player.setName(names.get(rnd.nextInt(names.size())));
        player.setCity(cities.get(rnd.nextInt(cities.size())));
        player.setAge(18 + rnd.nextInt(50));

        EntityManager em = emf.createEntityManager();
        try {
            userTransaction.begin();
            em.joinTransaction();
            
            em.persist(player);

            userTransaction.commit();
        } catch (Exception e) { // or use explicit exceptions
       
            if (userTransaction != null) {
                userTransaction.rollback();
            }
            
            throw e;
            
        } finally {            
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return player;
    }
}
