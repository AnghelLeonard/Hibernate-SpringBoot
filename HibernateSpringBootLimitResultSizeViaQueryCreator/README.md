**[How To Use Query Creation Mechanism For JPA To Limit Result Size](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLimitResultSizeViaQueryCreator)**

**Description:** Spring Data comes with the query creation mechanism for JPA that is capable to interpret a query method name and convert it into a SQL query in the proper dialect. This is possible as long as we respect the naming conventions of this mechanism. This is an application that exploit this mechanism to write queries that limit the result size. Basically, the name of the query method instructs Spring Data how to add the `LIMIT` (or similar clauses depending on the RDBMS) clause to the generated SQL queries.

**Key points:**
- define a Spring Data regular repository (e.g., `AuthorRepository`)
- write query methods respecting the query creation mechanism for JPA naming conventions     

**Examples:**\
    - `List<Author> findFirst5ByAge(int age);`\
    - `List<Author> findFirst5ByAgeGreaterThanEqual(int age);`\
    - `List<Author> findFirst5ByAgeLessThan(int age);`\
    - `List<Author> findFirst5ByAgeOrderByNameDesc(int age);`\
    - `List<Author> findFirst5ByGenreOrderByAgeAsc(String genre);`\
    - `List<Author> findFirst5ByAgeGreaterThanEqualOrderByNameAsc(int age);`\
    - `List<Author> findFirst5ByGenreAndAgeLessThanOrderByNameDesc(String genre, int age);`\
    - `List<AuthorDto> findFirst5ByOrderByAgeAsc();`\
    - `Page<Author> queryFirst10ByName(String name, Pageable p);`\
    - `Slice<Author> findFirst10ByName(String name, Pageable p);`
    
**The list of supported keywords is listed below:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLimitResultSizeViaQueryCreator/supported%20keywords%20inside%20method%20names.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
