**[Hibernate `hi/lo` Algorithm And External Systems Issue](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHiLoIssue)**

**Description:** This is a Spring Boot sample that exemplifies how the `hi/lo` algorithm may cause issues when the database is used by external systems as well. Such systems can safely generate non-duplicated identifiers (e.g., for inserting new records) only if they know about the `hi/lo` presence and its internal work. So, better rely on `pooled` or `pooled-lo` algorithm which doesn't cause such issues.

**Key points:**
- use the `SEQUENCE` generator type (e.g., in PostgreSQL)
- configure the `hi/lo` algorithm as in `Author.java` entity
- insert a few records via `hi/lo`
- insert a few records natively (this acts as an external system that relies on `NEXTVAL('hilo_sequence')` and is not aware of `hi/lo` presence and/or behavior)
     
**Output sample:** Running this application should result in the following error:\
`ERROR: duplicate key value violates unique constraint "author_pkey"`\
`Detail: Key (id)=(2) already exists.`

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
