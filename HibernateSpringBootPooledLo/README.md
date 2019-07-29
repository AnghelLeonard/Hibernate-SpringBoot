**[How To Generate Sequences Of Identifiers Via Hibernate `pooled-lo` Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPooledLo)**

**Description:** This is a Spring Boot example of using the `pooled-lo` algorithm. The `pooled-lo` is an optimization of `hi/lo` similar with `pooled`. Only that, the strategy of this algorithm fetches from the database the current sequence value and use it as the in-memory lowest boundary identifier. The number of in-memory generated identifiers is equal to `increment_size`.

**Key points:**\
     - use the `SEQUENCE` generator type (e.g., in PostgreSQL)\
     - configure the `pooled-lo` algorithm as in `Author.java` entity\
     - insert a few records via `pooled-lo`\
     - insert a few records natively (this acts as an external system that relies on `NEXTVAL('hilo_sequence')` and is not aware of `pooled-lo` presence and/or behavior)    

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
