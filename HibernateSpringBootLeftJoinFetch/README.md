**[LEFT JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLeftJoinFetch)**

**See also:**\
     - [How To Avoid LazyInitializationException Via JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFetch)\
     - [JOIN FETCH And DTOs](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaJoinFetch)\
     - [JOIN VS. JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinVSJoinFetch)

**Description:** Let's assume that we have two entities engaged in a one-to-many (or many-to-many) lazy bidirectional (or unidirectional) relationhip (e.g., `Author` has more `Book`). And, we want to trigger a single `SELECT` that fetches all `Author` and the corresponding `Book`. This is a job for `JOIN FETCH` which is converted behind the scene into a `INNER JOIN`. Being an `INNER JOIN`, the SQL will return only `Author` that have `Book`. If we want to return all `Author`, including those that doesn't have `Book`, then we can rely on `LEFT JOIN FETCH`. Similar, we can fetch all `Book`, including those with no registered `Author`.

**Key points:**\
     - define two related entities (e.g., `Author` and `Book` in a one-to-many lazy bidirectional relationship)\
     - write a JPQL `LEFT JOIN FETCH` to fetch all authors and books (fetch authors even if they don't have registered books)\
     - write a JPQL `LEFT JOIN FETCH` to fetch all books and authors (fetch books even if they don't have registered authors)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
