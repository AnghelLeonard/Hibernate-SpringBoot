**[Hibernate Envers Auditing (`spring.jpa.hibernate.ddl-auto=create`)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnvers)**
 
**Description:** Auditing is useful for maintaining history records. This can later help us in tracking user activities. 
 
**Key points:**
- for Maven, in `pom.xml` add the dependency `hibernate-envers` and JAXB API
- each entity that should be audited should be annotated with `@Audited`
- optionally, annotate entities with `@AuditTable` to rename the table used for auditing
- rely on `ValidityAuditStrategy` for fast database reads, but slower writes (slower than the default `DefaultAuditStrategy`)
      
-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>



