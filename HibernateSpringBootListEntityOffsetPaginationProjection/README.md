**[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `List<entity>` Via Projection (DTO)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationProjection)**
  
**Description:** This application fetches data as `List<entity>` via Spring Boot offset pagination. The `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, there is one query needed for fetching the data and one for counting the total number of records).

**Key points:**\
     - write a repository that extends `PagingAndSortingRepository`\
     - write a Spring projection for representing the entity and total number of records\
     - fetch data via a JPQL query (that includes `SELECT COUNT` subquery) into a `List<entity>`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
