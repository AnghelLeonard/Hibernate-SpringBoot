**[How To Use Subqeries in JPQL `WHERE`/`HAVING` Clause](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSubqueryInWhere)**
 
**Description:** This application is an example of using subqueries in JPQL `WHERE` clause (you can easily use it in `HAVING` clause as well).

**Key points:**\
Keep in mind that subqueries and joins queries may not be semantically equivalent. Joins may returns duplicates that can be removed via `DISTINCT`. 

Even if the Execution Plan is specific to the database, historically speaking joins are faster than subqueries among different databases, but this is not a rule (e.g., the amount of data may significantly influence the results). Of course, do not conclude that subqueries are just a replacement for joins that doesn't deserve attention. Tuning subqueries can increases their performance as well, but this is an SQL wide topic. So, benchmark! Benchmark! Benchmark!

**As a rule of thumb, prefer subqueries only if you cannot use joins, or if you can prove that they are faster than the alternative joins.**
          
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
