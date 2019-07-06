**[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `Page<entity>` Via Projection (DTO)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationProjection)**

**Description:** This application fetches data as `Page<entity>` via Spring Boot offset pagination. Use this only if the fetched data will be modified. Otherwise, fetch `Page<dto>` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPagination). The `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, there is one query needed for fetching the data and one for counting the total number of records).

**Key points:**\
     - define a Spring projection that maps the entity and the total number of records\
     - write a repository that extends `PagingAndSortingRepository`\
     - fetch data via a JPQL query (that includes counting) into a `List<projection>`, and a `Pageable`\
     - use the fetched `List<projection>` and `Pageable` to create a `Page<projection>`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
