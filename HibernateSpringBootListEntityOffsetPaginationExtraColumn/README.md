**[How To Use `SELECT COUNT` Subquery In Offset Pagination And Return `List<entity>` With Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationExtraColumn)**

**Description:** This application fetches data as `List<entity>` via Spring Boot offset pagination. The `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (one for fetching the data and one for counting the total number of records).

**Key points:**\
     - write a repository that extends `PagingAndSortingRepository`\
     - in the `entity` add a extra column for representing the total number of records and annotate it as `@Column(insertable = false, updatable = false)`\
     - fetch data via a native query (that includes `SELECT COUNT` subquery) into a `List<entity>`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
