**[How To Generate Sequences Of Identifiers Via Hibernate `hi/lo` Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHiLo)**

 **Note:** If systems external to your application need to insert rows in your tables then don't rely on `hi/lo` algorithm since, in such cases, it may cause errors resulted from generating duplicated identifiers. Rely on `pooled` or `pooled-lo` algorithms (optimizations of `hi/lo`).
 
**Description:** This is a Spring Boot example of using the `hi/lo` algorithm for generating 1000 identifiers in 10 database roundtrips for batching 1000 inserts in batches of 30. 

**Key points:**
- use the `SEQUENCE` generator type (e.g., in PostgreSQL)
- configure the `hi/lo` algorithm as in `Author.java` entity
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHiLo/Hibernate%20hilo%20algorithm.png)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

