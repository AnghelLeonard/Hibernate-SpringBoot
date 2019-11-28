**[How To Setup Spring Data JPA Auditing](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAudit)**
 
**Description:** Auditing is useful for maintaining history records. This can later help us in tracking user activities. 
 
**Key points:**
- create an `abstract` base entity (e.g., `BaseEntity`) and annotate it with `@MappedSuperclass` and `@EntityListeners({AuditingEntityListener.class})`
- in this base entity, add the following fields that will be automatically persisted:\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `@CreatedDate protected LocalDateTime created;`\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `@LastModifiedDate protected LocalDateTime lastModified;`\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `@CreatedBy protected U createdBy;`\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `@LastModifiedBy protected U lastModifiedBy;`
- enable auditing via `@EnableJpaAuditing(auditorAwareRef = "auditorAware")`
- provide an implementation for `AuditorAware` (this is needed for persisting the user that performed the modification; use Spring Security to return the currently logged-in user)
- expose this implementation via `@Bean`
- entites that should be audited should extend the base entity
- store the date-time in database in UTC

----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    



