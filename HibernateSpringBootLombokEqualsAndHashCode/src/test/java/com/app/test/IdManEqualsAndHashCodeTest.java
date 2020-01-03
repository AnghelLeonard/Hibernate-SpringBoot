package com.app.test;

import java.util.HashSet;
import java.util.Set;
import com.app.IdManBook;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IdManEqualsAndHashCodeTest {

    @Autowired
    private TestEntityManager entityManager;

    private static final IdManBook book = new IdManBook();
    private static final Set<IdManBook> books = new HashSet<>();

    @BeforeClass
    public static void setUp() {
        book.setId(1L);
        book.setTitle("Modern History");
        book.setIsbn("001-100-000-111");

        books.add(book);
    }

    @Test
    // Find in Set the book that has never been persisted
    // State transition at assertion point: NEW
    public void A_givenBookInSetWhenContainsThenTrue() throws Exception {

        assertTrue(books.contains(book));
    }

    @Test
    // Find in Set the book after it was persisted
    // State transition at first assertion point: NEW
    // State transition at second and third assertion point: MANAGED
    public void B_givenBookWhenPersistThenSuccess() throws Exception {

        assertNotNull(book.getId());

        entityManager.persistAndFlush(book);
        assertNotNull(book.getId());

        assertTrue(books.contains(book));
    }

    @Test
    // Find in Set the book after merge() was called - SELECT and UPDATE statement   
    // State transition at assertion point: MANAGED
    public void C_givenBookWhenMergeThenSuccess() throws Exception {

        book.setTitle("New Modern History");
        IdManBook mergedBook = entityManager.merge(book);
        entityManager.flush();

        assertTrue(books.contains(mergedBook));
    }

    @Test
    // Find in Set the book after find() was called - SELECT statement    
    // State transition at second assertion point: MANAGED
    public void D_givenBookWhenFindThenSuccess() throws Exception {

        IdManBook foundBook = entityManager.find(IdManBook.class, book.getId());
        entityManager.flush();

        assertTrue(books.contains(foundBook));
    }

    @Test
    // Find in Set the book after detach() was called
    // State transition at assertion point: DETACHED    
    public void E_givenBookWhenFindAndDetachThenSuccess() throws Exception {

        IdManBook foundBook = entityManager.find(IdManBook.class, book.getId());
        entityManager.detach(foundBook);

        assertTrue(books.contains(foundBook));
    }

    @Test
    // Find in Set the book after remove() was called - DELETE statement
    // State transition at assertion points: REMOVED    
    public void F_givenBookWhenFindAndRemoveThenSuccess() throws Exception {

        IdManBook foundBook = entityManager.find(IdManBook.class, book.getId());
        entityManager.remove(foundBook);
        entityManager.flush();

        assertTrue(books.contains(foundBook));

        books.remove(foundBook);

        assertFalse(books.contains(foundBook));
    }

}
