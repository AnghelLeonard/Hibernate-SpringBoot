**[Why To Avoid `JOIN FETCH` And DTO](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaJoinFetch)**

**See also:**
- [How To Avoid LazyInitializationException Via JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFetch)
- [LEFT JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLeftJoinFetch)
- [JOIN VS. JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinVSJoinFetch)
     
**Description:** Combining `JOIN FETCH` and DTO can be done under several constrains. Mainly, the JPQL containing the `JOIN FETCH` cannot be used to fetch only some columns from the involved entities (in such cases, `JOIN` is the proper choice). It must fetch all attributes of the involved entities. 

**But, even if the result set will be mapped to the DTO classes, it will be also loaded in the Persistent Context as entities. If we use `@Transactional(readOnly=false)` then the hydrated state will be kept in memory as well. If we use `@Transactional(readOnly=true)` then the hydrated state is discarded from memory, but the entities entries remains in `READ_ONLY` status in Persistent Context until the Persistent Context is cleared or closed.**.

**Key points:**
- define two related entities (e.g., `Author` and `Book` in a one-to-many lazy bidirectional relationship)
- define the proper DTOs classes (e.g., `BookDto` and `AuthorDto`)
- the `BookDto` and `AuthorDto` may map only the needed columns, but the triggered SQL will fetch all of them anyway
- write a JPQL `JOIN FETCH` to fetch an author including his books

**Constrains:**
- this is ok: `SELECT a FROM Author a JOIN FETCH a.books`
- this is not ok: `SELECT a.age as age FROM Author a JOIN FETCH a.books` -> *org.hibernate.QueryException: query specified join fetching, but the owner of the fetched association was not present in the select list*
- this is not ok: `SELECT a FROM Author a JOIN FETCH a.books.title` ->  *org.hibernate.QueryException: illegal attempt to dereference collection [author0_.id.books] with element property reference [title]*

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
