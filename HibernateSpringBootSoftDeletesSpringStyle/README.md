**[How To Implement Soft Deletes Via `SoftDeleteRepository` In Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSoftDeletesSpringStyle)**

**Note:** Spring Data built-in support for soft deletes is discussed in [DATAJPA-307](https://jira.spring.io/browse/DATAJPA-307).

**Description:** This application is an example of implementing soft deletes in Spring Data style via a repository named, `SoftDeleteRepository`. 

**Key points:**
- define an `abstract` class, `BaseEntity`, annotated with `@MappedSuperclass`
- in `BaseEntity` define a flag-field named `deleted` (default this field to `false` or in other words, not deleted)
- every entity that wants to take advantage of soft deletes should extend the `BaseEntity` classs
- write a `@NoRepositoryBean` named `SoftDeleteRepository` and extend `JpaRepository`
- override and implement the needed methods that provide the logic for soft deletes (check out the source code)
- repositories of entities should extend `SoftDeleteRepository`
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSoftDeletes/soft%20deletes.png)
 
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
