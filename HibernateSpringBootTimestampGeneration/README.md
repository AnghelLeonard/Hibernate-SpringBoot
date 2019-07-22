**[How To Add `created`, `lastModified` And `lastModifiedBy` In An Entity](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTimestampGeneration)**
 
**Description:** This application is an example of adding in an entity the fields, `created`, `lastModified` and `lastModifiedBy`. These fields have only getters and will be automatically generated/populated.

**Key points:**\
     - define a field named `created` and annotate it with `@CreationTimestamp`\
     - define a field named `lastModified` and annotate it with `@UpdateTimestamp`\
     - define a field named `lastModifiedBy` and annotate it with `@ModifiedBy`\
     - implement the `@ModifiedBy` annotation via `AnnotationValueGeneration`\
     - store the date-time in UTC
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
