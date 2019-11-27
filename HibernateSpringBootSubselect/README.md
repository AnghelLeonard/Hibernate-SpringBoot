**[How To Map An Entity To a Query (`@Subselect`) in a Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSubselect)**
 
**Note:** Consider using `@Subselect` only if using DTO, DTO and extra queries, or map a database view to an entity is not a solution.

**Description:** This application is an example of mapping an entity to a query via Hibernate, `@Subselect`. Mainly, we have two entities in a bidirectional *one-to-many* association. An `Author` has wrote several `Book`. The idea is to write a *read-only* query to fetch from `Author` only some fields (e.g., DTO), but to have the posibility to call `getBooks()` and fetch the `Book` in a lazy manner as well. As you know, a classic DTO cannot be used, since such DTO is not managed and we cannot navigate the associations (don’t support any managed associations to other entities). Via Hibernate `@Subselect` we can map a *read-only* and *immutable* entity to a query. This time, we can lazy navigate the associations. 

**Key points:**
- define a new entity that contains only the needed fields from the `Author` (including association to `Book`)
- for these fields, define only getters
- mark the entity as `@Immutable` since no write operations are allowed
- flush pending state transitions for the used entities by `@Synchronize`
- use `@Subselect` to write the needed query, map an entity to an SQL query

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
