**[How To Write Derived Count And Delete Queries](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDerivedCountAndDelete)**

**Description:** Spring Data comes with the Query Builder mechanism for JPA that is capable to interpret a query method name (known as a derived query) and convert it into a SQL query in the proper dialect. This is possible as long as we respect the naming conventions of this mechanism. Beside the well-known query of type `find...`, Spring Data supports derived count queries and derived delete queries.

**Key points:**
- a derived count query starts with `count...` (e.g., `long countByGenre(String genre)`) - Spring Data will generate a `SELECT COUNT(...) FROM ...` query 
- a derived delete query can return the number of deleted records or the a list of the deleted records
- a derived delete query that returns the number of deleted records starts with `delete...` (e.g., `long deleteByGenre(String genre)`) - Spring Data will trigger first a `SELECT` to fetch entities in the Persistence Context, and, afterwards, it triggers a `DELETE` for each entity that must be deleted 
- a derived delete query that returns the list of deleted records starts with `remove...` (e.g., `List<Author> removeByGenre(String genre)`) - Spring Data will trigger first a `SELECT` to fetch entities in the Persistence Context, and, afterwards, it triggers a `DELETE` for each entity that must be deleted 
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

