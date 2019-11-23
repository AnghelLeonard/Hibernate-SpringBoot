**[Fetching Associations In Batches Via `@BatchSize`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLoadBatchAssociation)**
 
**Description:** This application uses `@BatchSize` at class/entity-level and collection-level. Consider `Author` and `Book` entities invovled in a bidirectional-lazy `@OneToMany` association.

- First use case fetches all `Author` entities via a `SELECT` query. Further, calling the `getBooks()` method of the first `Author` entity will trigger another `SELECT` query that initializes the collections of the first three `Author` entities returned by the previous `SELECT` query. This is the effect of `@BatchSize` at `Author`'s collection-level.

- Second use case fetches all `Book` entities via a `SELECT` query. Further, calling the `getAuthor()` method of the first `Book` entity will trigger another `SELECT` query that initializes the authors of the first three `Book` entities returned by the previous `SELECT` query. This is the effect of `@BatchSize` at `Author` class-level.

**Note:** Fetching associated collections in the same query with their parent can be done via `JOIN FETCH` or entity graphs as well. Fetching children with their parents in the same query can be done via `JOIN FETCH`, entity graphs and `JOIN` as well.

**Key points:**
- `Author` and `Book` are in a lazy relationship (e.g., `@OneToMany` bidirectional relationship)
- `Author` entity is annotated with `@BatchSize(size = 3)`
- `Author`'s collection is annotated with `@BatchSize(size = 3)`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
