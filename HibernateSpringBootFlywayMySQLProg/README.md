**[How To Programmatically Setup Flyway And MySQL `DataSource`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootProgFlyway)**
 
**Note:** For production don't rely on `hibernate.ddl-auto` to create your schema. Remove (disable) `hibernate.ddl-auto` or set it to `validate` and rely on Flyway or Liquibase.

**Description:** This application is a kickoff for setting Flyway and MySQL `DataSource` programmatically.

**Key points:**\
     - for Maven, in `pom.xml`, add the Flyway dependency\
     - remove (disable) `spring.jpa.hibernate.ddl-auto`\
     - configure `DataSource` and Flyway programmatically
     
-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
