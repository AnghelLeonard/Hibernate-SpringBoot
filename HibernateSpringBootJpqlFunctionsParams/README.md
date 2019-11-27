**[How To Execute SQL Functions In JPQL Query](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunctionsParams)**

**Note**: Using SQL functions in the `WHERE` part (not in the `SELECT` part) of  query in JPA 2.1 can be done via `function()` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunction).

**Description:** Trying to use SQL functions (standard or defined) in JPQL queries may result in exceptions if Hibernate will not recognize them and cannot parse the JPQL query. For example, the MySQL, `concat_ws` function is not recognized by Hibernate. This application is a Spring Boot application based on Hibernate 5.3, that registers the `concat_ws` function via `MetadataBuilderContributor` and inform Hibernate about it via, `metadata_builder_contributor` property. This example uses `@Query` and `EntityManager` as well, so you can see two use cases.

**Key points:**
- use Hibernate 5.3 (or, to be precisely, 5.2.18) (e.g., use Spring Boot 2.1.0.RELEASE)
- implement `MetadataBuilderContributor` and register the `concat_ws` MySQL function
- in `application.properties`, set `spring.jpa.properties.hibernate.metadata_builder_contributor` to point out Hibernate to `MetadataBuilderContributor` implementation
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJpqlFunctionsParams/SQL%20functions%20in%20JPQL%20parameters.png)    

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
