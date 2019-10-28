**[Fetching Associations In Batches Via `@BatchSize`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLoadBatchAssociation)**

**Note:** Fetching associations in the same query with their parent can be done via `JOIN FETCH` or `@NamedEntityGraph`. But, Hibernate allows us to fetch associations in batches as well via `@BatchSize` annotation. This may be useful when `JOIN FETCH` and `@NamedEntityGraph` doesn't seem to help.
 
**Description:** This application fetches all `Author` entities via a `SELECT` query. Further, calling the `getBooks()` method of the first `Author` entity will trigger another `SELECT` query that initializes the association of the first three `Author` entities returned by the previous `SELECT` query. This is the effect of `@BatchSize`.

**Key points:**
- `Author` and `Book` are in a lazy relationship (e.g., `@OneToMany` bidirectional relationship)
- `Author` association is annotated with `@BatchSize(size = 3)`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
