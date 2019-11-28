
**[How To Populate a Child-Side Parent Association via Proxy](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPopulatingChildViaProxy)**

**Description:** A Hibernate proxy can be useful when a child entity can be persisted with a reference to its parent (`@ManyToOne` or `@OneToOne` association). In such cases, fetching the parent entity from the database (execute the `SELECT` statement) is a performance penalty and a pointless action, because Hibernate can set the underlying foreign key value for an uninitialized proxy.

**Key points:**
- rely on `EntityManager#getReference()`
- in Spring, use `JpaRepository#getOne()` -> used in this example
- in Hibernate, use `load()`
- assume two entities, `Author` and `Book`, involved in a unidirectional `@ManyToOne` association (`Author` is the parent-side)
- we fetch the author via a proxy (this will not trigger a `SELECT`), we create a new book, we set the proxy as the author for this book and we save the book (this will trigger an `INSERT` in the `book` table)
     
**Output example:**
- the console output will reveal that only an `INSERT` is triggered, and no `SELECT`

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
