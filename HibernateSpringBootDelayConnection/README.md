**[How To Delay Connection Acquisition As Needed (Hibernate 5.2.10)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDelayConnection)**

**Description:** This is a Spring Boot example that exploits Hibernate 5.2.10 capability of delaying the connection acquisition as needed. Normally, a database connection is aquried immediately after calling a method annotated with `@Transactional`. If this method contains some time-consuming tasks before the first SQL statement then the connection is holded for nothing. But, Hibernate 5.2.10 allows us to delay the connection acquisition as needed. This example rely on HikariCP as the default connection pool for Spring Boot.

**Key points:**\
     - set `spring.datasource.hikari.auto-commit=false` in application.properties\
     - set `spring.jpa.properties.hibernate.connection.provider_disables_autocommit=true` in application.properties
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDelayConnection/sample1.png)
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDelayConnection/sample2.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
