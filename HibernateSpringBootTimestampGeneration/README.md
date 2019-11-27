**[How To Add `created`, `createdBy`, `lastModified` And `lastModifiedBy` In Entities Via Hibernate](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTimestampGeneration)**
 
**Note:** The same thing can be obtained via Spring Data JPA auditing as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAudit).

**Description:** This application is an example of adding in an entity the fields, `created`, `createdBy`, `lastModified` and `lastModifiedBy` via Hibernate support. These fields will be automatically generated/populated.

**Key points:**
- write an `abstract` class (e.g., `BaseEntity`) annotated with `@MappedSuperclass`
- in this `abstract` class, define a field named `created` and annotate it with the built-in `@CreationTimestamp` annotation
- in this `abstract` class, define a field named `lastModified` and annotate it with the built-in `@UpdateTimestamp` annotation
- in this `abstract` class, define a field named `createdBy` and annotate it with the `@CreatedBy` annotation
- in this `abstract` class, define a field named `lastModifiedBy` and annotate it with the `@ModifiedBy` annotation
- implement the `@CreatedBy` annotation via `AnnotationValueGeneration`
- implement the `@ModifiedBy` annotation via `AnnotationValueGeneration`
- every entity that want to take advantage of `created`, `createdBy`, `lastModified` and `lastModifiedBy` will extend the `BaseEntity`
- store the date-time in UTC
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

