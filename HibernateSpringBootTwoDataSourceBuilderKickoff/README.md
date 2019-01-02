**[How To Configure Two Data Sources With Two Connection Pools](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTwoDataSourceBuilderKickoff)**

**Description:** This is a kickoff application that uses two data sources (two MySQL databases, one named `players_db` and one named `coaches_db`) with two connection pools (each database uses its own HikariCP connection pool with different settings). Based on the above recipes is pretty easy to configure two connection pools from two different providers as well.

**Key points:**\
     - in pom.xml, add the `spring-boot-configuration-processor` dependency\
     - in application.properties, configure two HikariCP connection pools via a two custom prefixes, e.g., `app.datasource.ds1` and `app.datasource.ds2`\
     - write a `@Bean` that returns the first `DataSource` and mark it as `@Primary`\
     - write another `@Bean` that returns the second `DataSource`\
     - configure two `EntityManagerFactory` and point out the packages to scan for each of them\
     - put the domains and repositories for each `EntityManager` in the right packages
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootTwoDataSourceBuilderKickoff/sample.png)

---------------------------------------------

**You may like to try as well**:

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
