**[How To Use `JOIN FETCH` And `Pageable` Pagination](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFetchPageable)**

**Description:** Trying to combine `JOIN FETCH`/`LEFT JOIN FETCH` and `Pageable` results in an exception of type `org.hibernate.QueryException: query specified join fetching, but the owner of the fetched association was not present in the select list`. This application is a sample of how to avoid this exception.

**Key points:**
- use `countQuery`
- use entity graph
     
**Note:** Fixing the above exception will lead to an warning of type HHH000104, `firstResult / maxResults specified with collection fetch; applying in memory!`. If this warning is a performance issue, and most probably it is, then follow by reading [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHHH000104).     

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
