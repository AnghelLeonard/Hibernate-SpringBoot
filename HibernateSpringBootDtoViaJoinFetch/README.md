**[JOIN FETCH And DTOs](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaJoinFetch)**

**Description:** Combining `JOIN FETCH` and DTOs can be done under several constrains. Mainly, the JPQL containing the `JOIN FETCH` cannot be used to fetch only some columns from the involved entities (in such cases, `JOIN` is the proper choice). It must fetch all attributes of the involved entities. 

**Key points:**\
     - define two related entities (e.g., `Author` and `Book` in a one-to-many lazy bidirectional relationship)\
     - define the proper DTOs classes (e.g., `BookDto` and `AuthorDto`)\
     - write a JPQL `JOIN FETCH` to fetch an author including his books

**Constrains:**\
     - this is ok: `SELECT a FROM Author a JOIN FETCH a.books`\
     - this is not ok: `SELECT a.age as age FROM Author a JOIN FETCH a.books` -> *org.hibernate.QueryException: query specified join fetching, but the owner of the fetched association was not present in the select list*\
     - this is not ok: `SELECT a FROM Author a JOIN FETCH a.books.title` ->  *org.hibernate.QueryException: illegal attempt to dereference collection [author0_.id.books] with element property reference [title]*

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
