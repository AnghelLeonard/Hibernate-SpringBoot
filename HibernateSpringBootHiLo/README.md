**[How To Generate Sequences Of Identifiers Via Hibernate `hi/lo` Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHiLo)**

 **Note:** If systems external to your application need to insert rows in your tables then don't rely on `hi/lo` since, in such cases, it may cause errors resulted from generating duplicated identifiers. Rely on `pooled` or `pooled-lo`.
 
**Description:** This is a Spring Boot example of using the `hi/lo` algorithm for generating 1000 identifiers in 10 database roundtrips for batching 1000 inserts in batches of 30 inserts. The `hi/lo` is a Hibernate algorithm is an optimization algorithm for generating sequences of identifiers.

**Key points:**\
     - use the `SEQUENCE` generator type (e.g., in PostgreSQL)\
     - configure the `hi/lo` algorithm as in `Author.java` entity
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHiLo/Hibernate%20hilo%20algorithm.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
