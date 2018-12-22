**[How To Execute SQL Functions With Multiple Parameters in a JPQL Query](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunctionsParams)**

**Description:** Trying to use SQL functions (MySQL, PostgreSQL, etc) in JPQL queries may result in exceptions if Hibernate will not recognize them and cannot parse the JPQL query. For example, the MySQL, `concat_ws` function is not recognized by Hibernate. This application is a Spring Boot application based on Hibernate 5.3, that registers the `concat_ws` function via `MetadataBuilderContributor` and inform Hibernate about it via, `metadata_builder_contributor` property. This example uses `@Query` and `EntityManager` as well, so you can see two use cases.

**Key points:**\
     - use Hibernate 5.3 (e.g., use Spring Boot 2.1.0.RELEASE)\
     - implement `MetadataBuilderContributor` and register the `concat_ws` MySQL function\
     - in application.properties, set `spring.jpa.properties.hibernate.metadata_builder_contributor` to point out to `MetadataBuilderContributor` implementation
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJpqlFunctionsParams/sample.png)    
