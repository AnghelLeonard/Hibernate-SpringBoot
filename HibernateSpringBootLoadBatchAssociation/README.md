**[Fetching Associations In Batches Via `@BatchSize`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLoadBatchAssociation)**
 
**Description:** This application uses Hibernate specific `@BatchSize` at class/entity-level and collection-level. Consider `Author` and `Book` entities invovled in a bidirectional-lazy `@OneToMany` association.

- First use case fetches all `Author` entities via a `SELECT` query. Further, calling the `getBooks()` method of the first `Author` entity will trigger another `SELECT` query that initializes the collections of the first three `Author` entities returned by the previous `SELECT` query. This is the effect of `@BatchSize` at `Author`'s collection-level.

- Second use case fetches all `Book` entities via a `SELECT` query. Further, calling the `getAuthor()` method of the first `Book` entity will trigger another `SELECT` query that initializes the authors of the first three `Book` entities returned by the previous `SELECT` query. This is the effect of `@BatchSize` at `Author` class-level.

**Note:** Fetching associated collections in the same query with their parent can be done via `JOIN FETCH` or entity graphs as well. Fetching children with their parents in the same query can be done via `JOIN FETCH`, entity graphs and `JOIN` as well.

**Key points:**
- `Author` and `Book` are in a lazy relationship (e.g., `@OneToMany` bidirectional relationship)
- `Author` entity is annotated with `@BatchSize(size = 3)`
- `Author`'s collection is annotated with `@BatchSize(size = 3)`

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

