**[How To Execute SQL Functions With Multiple Parameters in a JPQL Query](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunctionsParams)**

**Description:** Trying to use SQL functions (MySQL, PostgreSQL, etc) in JPQL queries may result in exceptions if Hibernate will not recognize them and cannot parse the JPQL query. For example, the MySQL, `concat_ws` function is not recognized by Hibernate. This application is a Spring Boot application based on Hibernate 5.3, that registers the `concat_ws` function via `MetadataBuilderContributor` and inform Hibernate about it via, `metadata_builder_contributor` property. This example uses `@Query` and `EntityManager` as well, so you can see two use cases.

**Key points:**\
     - use Hibernate 5.3 (or, to be precisely, 5.2.18) (e.g., use Spring Boot 2.1.0.RELEASE)\
     - implement `MetadataBuilderContributor` and register the `concat_ws` MySQL function\
     - in application.properties, set `spring.jpa.properties.hibernate.metadata_builder_contributor` to point out to `MetadataBuilderContributor` implementation
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJpqlFunctionsParams/SQL%20functions%20in%20JPQL%20parameters.png)    

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
