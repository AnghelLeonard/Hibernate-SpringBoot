**[How To Generate Sequences Of Identifiers Via Hibernate `pooled-lo` Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPooledLo)**

**Note:** Rely on `pooled-lo` or `pooled` especially if, beside your application, external systems needs to insert rows in your tables. Don't rely on `hi/lo` since, in such cases, it may cause errors resulted from generating duplicated identifiers.

**Description:** This is a Spring Boot example of using the `pooled-lo` algorithm. The `pooled-lo` is an optimization of `hi/lo` similar with `pooled`. Only that, the strategy of this algorithm fetches from the database the current sequence value and use it as the in-memory lowest boundary identifier. The number of in-memory generated identifiers is equal to `increment_size`.

**Key points:**
- use the `SEQUENCE` generator type (e.g., in PostgreSQL)
- configure the `pooled-lo` algorithm as in `Author.java` entity
- insert a few records via `pooled-lo`
- insert a few records natively (this acts as an external system that relies on `NEXTVAL('hilo_sequence')` and is not aware of `pooled-lo` presence and/or behavior)    

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

