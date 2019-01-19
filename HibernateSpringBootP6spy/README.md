
**[How To Set Up p6spy in Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootP6spy)**

**Description:** This is a Spring Boot application that uses [P6Spy](https://github.com/p6spy/p6spy). *P6Spy is a framework that enables database data to be seamlessly intercepted and logged with no code changes to the application.*

**Key points:**\
     - in pom.xml, add the P6Spy Maven dependency\
     - in application.properties, set up JDBC URL as, `jdbc:p6spy:mysql://localhost:3306/db_users`\
     - in application.properties, set up driver class name as, `com.p6spy.engine.spy.P6SpyDriver`\
     - in the application root folder add the file spy.properties (this file contains P6Spy configurations); in this application, the logs will be outputed to console, but you can easy switch to a file; more details about P6Spy configurations can be found in documentation

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootP6spy/Hibernate%20SpringBoot%20P6spy.png)
