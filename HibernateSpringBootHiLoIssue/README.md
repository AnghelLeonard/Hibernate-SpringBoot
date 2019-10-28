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

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
