**[How To Use Query Creation Mechanism For JPA To Limit Result Size](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLimitResultSizeViaQueryCreator)**

**Description:** Spring Data comes with the query creation mechanism for JPA that is capable to interpret a query method name and convert it into a SQL query. This is possible as long as we respect the naming conventions of this mechanism. This is an application that exploit this mechanism to write queries that limit the result size. Basically, the name of the query method instructs Spring Data how to add the `LIMIT` (or similar clauses depending on the RDBMS) clause to the generated SQL queries.

**Key points:**\
     - define a Spring Data classic repository (e.g., `AuthorRepository`)\
     - write query methods respecting the query creation mechanism for JPA naming conventions\
     - populate the database with some records, in this case via, `data-mysql.sql` file\
     - run the queries and check the output sample below

**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLimitResultSizeViaQueryCreator/query%20creation%20for%20limiting%20result%20size.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
