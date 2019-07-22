
**[How To Populate a Child-Side Parent Association via Proxy](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPopulatingChildViaProxy)**

**Description:** A `Proxy` can be useful when a child entity can be persisted with a reference to its parent. In such cases, fetching the parent entity from the database (execute the `SELECT` statement) is a performance penalty and a pointless action. Hibernate can set the underlying foreign key value for an uninitialized `Proxy`.

**Key points:**\
     - rely on `EntityManager#getReference()`\
     - in Spring, use `JpaRepository#getOne()` -> used in this example\
     - in Hibernate, use `load()`\
     - assume two entities, `Author` and `Book`, involved in a unidirectional `@ManyToOne` relationship (`Author` is the parent-side)\
     - we fetch the author via a `Proxy` (this will not trigger a `SELECT`), we create a new book, we set the `Proxy` as the author for this book and we save the book (this will trigger an `INSERT` in the `book` table)
     
**Output example:**\
     - the console output will reveal that only an `INSERT` is triggered, not the `SELECT`

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
