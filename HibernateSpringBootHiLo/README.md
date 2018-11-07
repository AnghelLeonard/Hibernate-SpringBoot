**[How To Fetch Primary Keys Via Hibernate hi/lo Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHiLo)**

**Description:** This is a Spring Boot example of using the hi/lo algorithm for fetching 1000 PKs in 10 database roundtrips for batching 1000 inserts in batches of 10 inserts. The hi/lo algorithm is an optimization algorithm for generating sequences of identifiers.

**Key points:**\
     - use the `SEQUENCE` generator type (e.g., in PostgreSQL)\
     - configure the hi/lo algorithm as in `Player.java` entity
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHiLo/sample.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
