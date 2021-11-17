**[How To Intercept The Generated SQL For Logging Or Altering](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootInterceptSql)**
 
**Description:** Sometimes we need to intercept the generated SQL that originates from Spring Data, `EntityManager`, Criteria API, `JdbcTemplate` and so on. This can be done as in this sample application. After interception, you can log, modify or even return a brand new SQL that will be executed in the end.

**Key points:**
- define an implementation of Hibernate `StatementInspector` SPI
- configure this SPI in `application.properties` via `spring.jpa.properties.hibernate.session_factory.statement_inspector`

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

