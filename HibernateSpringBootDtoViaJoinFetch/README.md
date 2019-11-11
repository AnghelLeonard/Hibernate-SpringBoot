**[Why To Avoid Combining `JOIN FETCH` And Spring projections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaJoinFetch)**

**See also:**
- [How To Avoid LazyInitializationException Via JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFetch)
- [LEFT JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLeftJoinFetch)
- [JOIN VS. JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinVSJoinFetch)
     
**Description:** Combining `JOIN FETCH` and Spring projections (DTO) it is possible but you should't do it.
Mainly, the JPQL containing the `JOIN FETCH` cannot be used to fetch only some columns from the involved parent-child entities (in such cases, `JOIN` is the proper choice). It must fetch all attributes of the involved entities. No matter if your projection has only one attribute from the parent and one from the child, or it mirrors the whole attributes of parent-child entitites, the Persistent Context will be populated with the same data. Moreover, using `DISTINCT` is not possible, therefore duplicates are allowed.

**Even if the result set will be mapped to the projection, it will be also loaded in the Persistent Context as entities. If we use `@Transactional(readOnly=false)` then the *hydrated state* will be kept in memory as well and entities will be in the `MANAGED` status. If we use `@Transactional(readOnly=true)` then the *hydrated state* is discarded from memory, but the entities entries remains in `READ_ONLY` status in Persistent Context until the Persistent Context is cleared or closed. So, if your projection mirrors the involved entities then better rely on read-only entitites and don't create the projection. If your projection contains a subset of attributes then avoid `JOIN FETCH` and use other approaches.**.

**Key points:**
- this works: `SELECT a FROM Author a JOIN FETCH a.books`
- this doesn't work: `SELECT a.age as age FROM Author a JOIN FETCH a.books` -> *org.hibernate.QueryException: query specified join fetching, but the owner of the fetched association was not present in the select list*
- this doesn't work: `SELECT a FROM Author a JOIN FETCH a.books.title` ->  *org.hibernate.QueryException: illegal attempt to dereference collection [author0_.id.books] with element property reference [title]*

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
