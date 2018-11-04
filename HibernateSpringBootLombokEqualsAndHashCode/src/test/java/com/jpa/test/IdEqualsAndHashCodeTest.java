package com.jpa.test;

import com.jpa.IdProduct;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IdEqualsAndHashCodeTest {

    @Autowired
    private TestEntityManager entityManager;

    private static final IdProduct product = new IdProduct();
    private static final Set<IdProduct> products = new HashSet<>();    
    
    @BeforeClass
    public static void setUp() {
        product.setName("T-shirt");
        product.setCode("233-E3");

        products.add(product);
    }

    @Test
    // Find the Product that has never been persisted
    // Transition state at assert point: NEW
    public void A_givenProductInSetWhenContainsThenTrue() {
                        
        assertTrue(products.contains(product));
    }

    @Test(expected = java.lang.AssertionError.class)
    // Find the Product after persist
    // Transition state at first assert point: NEW
    // Transition state at second and third assert point: MANAGED    
    public void B_givenProductWhenPersistThenSuccess() {

        assertNull(product.getId());
        
        entityManager.persistAndFlush(product);
        assertNotNull(product.getId());      

        assertTrue(products.contains(product));
    }

    @Test(expected = java.lang.AssertionError.class)
    // Find the Product after a merge() - UPDATE statement
    // Transition state at first assert point: DETACHED
    // Transition state at second assert point: MANAGED
    public void C_givenProductWhenMergeThenSuccess() {

        product.setName("Hat");
        assertTrue(products.contains(product));
        
        IdProduct mergedProduct = entityManager.merge(product);
        entityManager.flush();

        assertTrue(products.contains(mergedProduct));
    }            
   
    @Test(expected = java.lang.AssertionError.class)
    // Find the Product after a find() - SELECT statement
    // Transition state at first assert point: DETACHED
    // Transition state at second assert point: MANAGED
    public void D_givenProductWhenFindThenSuccess() {

        assertTrue(products.contains(product));
        
        IdProduct foundProduct = entityManager
                .find(IdProduct.class, product.getId());
        entityManager.flush();
        
        assertTrue(products.contains(foundProduct));
    }
    
    @Test(expected = java.lang.AssertionError.class)
    // Find the Product after an explicit detach
    // Transition state at assert point: DETACHED    
    public void E_givenProductWhenFindAndDetachThenSuccess() {

        IdProduct foundProduct = entityManager
                .find(IdProduct.class, product.getId());
        entityManager.detach(foundProduct);
        
        assertTrue(products.contains(foundProduct));
    }
     
    @Test(expected = java.lang.AssertionError.class)
    // Find the Product after a remove() - DELETE statement
    // Transition state at assert points: REMOVED    
    public void F_givenProductWhenFindAndRemoveThenSuccess() {

        IdProduct foundProduct = entityManager
                .find(IdProduct.class, product.getId());
        entityManager.remove(foundProduct);
        entityManager.flush();
        
        assertTrue(products.contains(foundProduct));
        
        products.remove(foundProduct);
        
        assertFalse(products.contains(foundProduct));
    }

}
