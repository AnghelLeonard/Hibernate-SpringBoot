**[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `List<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationExtraColumn)**
 
**Description:** This application fetches data as `List<entity>` via Spring Boot offset pagination. The `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, one query is needed for fetching the data and one for counting the total number of records).

**Key points:**
- write a repository that extends `PagingAndSortingRepository`
- in the `entity`, add an extra column for representing the total number of records and annotate it as `@Column(insertable = false, updatable = false)`
- fetch data via a native query (that includes `SELECT COUNT` subquery) into a `List<entity>` 

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

