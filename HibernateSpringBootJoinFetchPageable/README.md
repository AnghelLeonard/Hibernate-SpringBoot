**[How To Use `JOIN FETCH` And `Pageable` Pagination](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFetchPageable)**

**Description:** Trying to combine `JOIN FETCH`/`LEFT JOIN FETCH` and `Pageable` results in an exception of type `org.hibernate.QueryException: query specified join fetching, but the owner of the fetched association was not present in the select list`. This application is a sample of how to avoid this exception.

**Key points:**
- use `countQuery`
- use entity graph
     
**Note:** Fixing the above exception will lead to an warning of type HHH000104, `firstResult / maxResults specified with collection fetch; applying in memory!`. If this warning is a performance issue, and most probably it is, then follow by reading [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHHH000104).     

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

