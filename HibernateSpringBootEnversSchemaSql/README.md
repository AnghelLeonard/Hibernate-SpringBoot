**[Hibernate Envers Auditing (`schema-mysql.sql`)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnversSchemaSql)**
 
**Description:** Auditing is useful for maintaining history records. This can later help us in tracking user activities. 
 
**Key points:**\
     - in `pom.xml` add the dependency `hibernate-envers`\
     - each entity that should be audited should be annotated with `@Audited`\
     - optionally, annotate entities with `@AuditTable` to rename the table used for auditing\
     - rely on `ValidityAuditStrategy`\
     - remove (disable) `spring.jpa.hibernate.ddl-auto` for avoiding schema generated from JPA annotations\
     - create `schema-mysql.sql` and provide the SQL statements needed by Hibernate Envers     
     
-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>



