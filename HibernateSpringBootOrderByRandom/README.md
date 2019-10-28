**[How To Shuffle Small Result Sets](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOrderByRandom)**
 
**Description:** This application is an example of shuffling small results sets. **DO NOT USE** this technique for large results sets, since is extremely expensive.
 
**Key points:**
- write a JPQL `SELECT` query and append to it `ORDER BY RAND()`
- each RDBMS support a function similar to `RAND()` (e.g., in PostgreSQL is `random()`)
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
