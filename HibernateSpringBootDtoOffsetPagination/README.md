**[How To Use Spring Boot Offset Pagination And `Page<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOffsetPagination)**

**Description:** This application fetches data as `Page<dto>` via Spring Boot offset pagination. Most of the time data that should be paginated is read-only data. But, fetching data into entities should be done only if we plan to modify that data, therefore, fetching read only data as `Page<entity>` is not preferable.

**Key points:**\
     - create a Spring projection (DTO) to contains getters only for the data that should be fetched\
     - write a repository that extends `PagingAndSortingRepository`\
     - fetch data via a native query that includes counting into a `List<dto>` and a `Pageable`\
     - use `List<dto>` and `Pageable` to create a `Page<dto>`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
