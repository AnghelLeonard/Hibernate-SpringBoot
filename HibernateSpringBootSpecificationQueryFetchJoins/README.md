**[How To Create `Specification` Query Fetch Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSpecificationQueryFetchJoins)**

**Description:** This application contains two examples of how to define `JOIN` in `Specification` to emulate JPQL join-fetch operations.

**Key points:**\
     - the first approach trigger two `SELECT` statements and the pagination is done in memory (very bad!)\
     - the second approach trigger three `SELECT` statements but the pagination is done in the database\
     - in both approaches the `JOIN` is defined in a `Specification` implementation

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
