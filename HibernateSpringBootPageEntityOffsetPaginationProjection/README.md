**[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `Page<projection>` That Maps Entities And The Total Number Of Records Via Projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationProjection)**
 
**Description:** This application fetches data as `Page<projection>` via Spring Boot offset pagination. The projection maps the entity and the total number of records. This information is fetched in a single database rountrip because the `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. 

**Key points:**
- define a Spring projection that maps the entity and the total number of records
- write a repository that extends `PagingAndSortingRepository`
- fetch data via a JPQL query into a `List<projection>`
- use the fetched `List<projection>` and `Pageable` to create a `Page<projection>`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
