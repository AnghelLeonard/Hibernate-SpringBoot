**[How To View Binding Params Via `profileSQL=true` In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLogBindingParametersMySQL)**

**Description:** View the prepared statement binding parameters via `profileSQL=true` in MySQL.

**Key points:**
- in `application.properties` append `logger=Slf4JLogger&profileSQL=true` to the JDBC URL (e.g., `jdbc:mysql://localhost:3306/bookstoredb?createDatabaseIfNotExist=true&logger=Slf4JLogger&profileSQL=true`)
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLogBindingParametersMySQL/display%20binding%20via%20profileSQL%3Dtrue.png)
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
