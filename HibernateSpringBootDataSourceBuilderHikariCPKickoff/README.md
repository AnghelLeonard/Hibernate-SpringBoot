**[How To Configure HikariCP Via DataSourceBuilder](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderHikariCPKickoff)**

**If you use the `spring-boot-starter-jdbc` or `spring-boot-starter-data-jpa` "starters", you automatically get a dependency to HikariCP**

**Description:** This is a kickoff application that set up HikariCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. Check the HickariCP report revealing the connection pool status.

**Key points:**\
     - in pom.xml, add the `spring-boot-configuration-processor` dependency\
     - in application.properties, configure HikariCP via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHikariCPPropertiesKickoff/sample.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
