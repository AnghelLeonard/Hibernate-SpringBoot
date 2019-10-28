**[How To Customize ViburDBCP Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderViburDBCPKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.
 
**Description:** This is a kickoff application that set up ViburDBCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purposes, the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**
- in `pom.xml` add the ViburDBCP dependency
- in `application.properties`, configure ViburDBCP via a custom prefix, e.g., `app.datasource.*`
- write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderViburDBCPKickoff/ViburDBCP%20log%20trace.png)

------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
