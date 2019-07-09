package com.app.test;

import com.app.LombokDefaultBook;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LombokDefaultEqualsAndHashCodeTest {

    @Autowired
    private TestEntityManager entityManager;

    private static final LombokDefaultBook book = new LombokDefaultBook();
    private static final Set<LombokDefaultBook> books = new HashSet<>();

    @BeforeClass
    public static void setUp() {
        book.setTitle("Java Modern Challenge");
        book.setIsbn("45522-2322GH-23344");

        books.add(book);
    }

    @Test
    // Find the Book that has never been persisted
    // Transition state at assert point: NEW
    public void A_givenBookInSetWhenContainsThenTrue() {

        assertTrue(books.contains(book));
    }

    @Test(expected = java.lang.AssertionError.class)
    // Find the Book after persist
    // Transition state at first assert point: NEW
    // Transition state at second and third assert point: MANAGED    
    public void B_givenBookWhenPersistThenSuccess() {

        assertNull(book.getId());

        entityManager.persistAndFlush(book);
        assertNotNull(book.getId());

        assertTrue(books.contains(book));
    }

    @Test(expected = java.lang.AssertionError.class)
    // Find the Book after a merge() - UPDATE statement
    // Transition state at first assert point: DETACHED
    // Transition state at second assert point: MANAGED
    public void C_givenBookWhenMergeThenSuccess() {

        book.setTitle("Mastering JSF 2.2");
        assertTrue(books.contains(book));

        LombokDefaultBook mergedBook = entityManager.merge(book);
        entityManager.flush();

        assertTrue(books.contains(mergedBook));
    }

    @Test(expected = java.lang.AssertionError.class)
    // Find the Book after a find() - SELECT statement
    // Transition state at first assert point: DETACHED
    // Transition state at second assert point: MANAGED
    public void D_givenBookWhenFindThenSuccess() {

        assertTrue(books.contains(book));

        LombokDefaultBook foundBook = entityManager
                .find(LombokDefaultBook.class, book.getId());
        entityManager.flush();

        assertTrue(books.contains(foundBook));
    }

    @Test(expected = java.lang.AssertionError.class)
    // Find the Book after an explicit detach
    // Transition state at assert point: DETACHED    
    public void E_givenBookWhenFindAndDetachThenSuccess() {

        LombokDefaultBook foundBook = entityManager
                .find(LombokDefaultBook.class, book.getId());
        entityManager.detach(foundBook);

        assertTrue(books.contains(foundBook));
    }

    @Test(expected = java.lang.AssertionError.class)
    // Find the Book after a remove() - DELETE statement
    // Transition state at assert points: REMOVED    
    public void F_givenBookWhenFindAndRemoveThenSuccess() {

        LombokDefaultBook foundBook = entityManager
                .find(LombokDefaultBook.class, book.getId());
        entityManager.remove(foundBook);
        entityManager.flush();

        assertTrue(books.contains(foundBook));

        books.remove(foundBook);

        assertFalse(books.contains(foundBook));
    }

}
