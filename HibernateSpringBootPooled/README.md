**[How To Generate Sequences Of Identifiers Via Hibernate `pooled` Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPooled)**
 
 **Note:** Rely on `pooled-lo` or `pooled` especially if, beside your application, external systems needs to insert rows in your tables. Don't rely on `hi/lo` since, in such cases, it may cause errors resulted from generating duplicated identifiers.
 
**Description:** This is a Spring Boot example of using the `pooled` algorithm. The `pooled` is an optimization of `hi/lo`. This algorithm fetched from the database the current sequence value as the top boundary identifier (the current sequence value is computed as the previous sequence value + `increment_size`). This way, the application will use in-memory identifiers generated between the previous top boundary exclusive (aka, lowest boundary) and the current top boundary inclusive. 

**Key points:**
- use the `SEQUENCE` generator type (e.g., in PostgreSQL)
- configure the `pooled` algorithm as in `Author.java` entity
- insert a few records via `pooled`
- insert a few records natively (this acts as an external system that relies on `NEXTVAL('hilo_sequence')` and is not aware of `pooled` presence and/or behavior)
     
**Conclusion:** In contrast to the classical `hi/lo` algorithm, the Hibernate `pooled` algorithm doesn't cause issues to external systems that wants to interact with our tables. In other words, external systems can concurrently insert rows in the tables relying on `pooled` algorithm. Nevertheless, old versions of Hibernate can raise exceptions caused by `INSERT` statements triggered by external systems that uses the lowest boundary as identifier. This is a good reason to update to Hibernate latest versions (e.g., Hibernate 5.x), which have fixed this issue.

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

