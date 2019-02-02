package com.sample.boundary;

import com.sample.model.Player;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("player")
@Stateless
public class PlayerResource {

    private static final Logger logger = Logger.getLogger(PlayerResource.class.getName());
    
    @POST
    public String generatePlayer() {

        Player player = new Player();
        player.setName("Some Name");
        player.setCity("Some city");
        player.setAge(20);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(player);
            tx.commit();

            return "Success";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception: {0}", e);

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            if (em.isOpen()) {              
                em.close();
            }          
        }

        return "Failure";
    }
}
