**[Spring Data JPA Auditing](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAudit)**
 
**Description:** Auditing is useful for maintaining history records. This can later help us in tracking user activities. 
 
**Key points:**\
     - create an abstract base entity (e.g., `BaseEntity`) and annotate it with `@MappedSuperclass` and `@EntityListeners({AuditingEntityListener.class})`\
     - in this base entity, add the following fields that will be automatically persisted: `@CreatedDate protected LocalDateTime createdAt;`, `@LastModifiedDate protected LocalDateTime updatedAt;`, `@CreatedBy protected U createdBy;` and `@LastModifiedBy protected U modifiedBy;`\
     - enable auditing via `@EnableJpaAuditing(auditorAwareRef = "auditorAware")`\
     - provide an implementation for `AuditorAware` (this is needed for persisting the user that performed the modification; use Spring Security to return the currently logged-in user)\
     - expose this implementation via `@Bean`\
     - entites that should be audited should extend the base entity
          
-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>


