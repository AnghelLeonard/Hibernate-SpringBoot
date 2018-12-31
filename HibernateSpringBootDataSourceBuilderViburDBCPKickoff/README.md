**[How To Configure ViburDBCP Via DataSourceBuilder](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderViburDBCPKickoff)**

**Description:** This is a kickoff application that set up ViburDBCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**\
     - in pom.xml, add the `spring-boot-configuration-processor` dependency\
     - in pom.xml add the ViburDBCP dependency\
     - in application.properties, configure ViburDBCP via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderViburDBCPKickoff/sample.png)
