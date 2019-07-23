**[How To Add `created`, `lastModified` And `lastModifiedBy` In An Entity Via Hibernate](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTimestampGeneration)**
 
**Description:** This application is an example of adding in an entity the fields, `created`, `lastModified` and `lastModifiedBy` via Hibernate support. These fields will be automatically generated/populated.

**Key points:**\
     - write an abstract class (e.g., `BaseEntity`) annotated with `@MappedSuperclass`\
     - in this abstract class, define a field named `created` and annotate it with the built-in `@CreationTimestamp` annotation\
     - in this abstract class, define a field named `lastModified` and annotate it with the built-in `@UpdateTimestamp` annotation\
     - in this abstract class, define a field named `lastModifiedBy` and annotate it with the `@ModifiedBy` annotation\
     - implement the `@ModifiedBy` annotation via `AnnotationValueGeneration`\
     - every entity that want to take advantage of `created`, `lastModified` and `lastModifiedBy` will extend the `BaseEntity`\
     - store the date-time in UTC
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
