**[`IN` Clause Parameter Padding](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootINListPadding)**
 
**Description:** This application is an example of using Hibernate `IN` cluase parameter padding. This way we can reduce the number of Execution Plans. Mainly, Hibernate is padding parameters as follows: 
 
- for 3 and 4 parameters -> it uses 4 bind parameters (2^2)
- for 5, 6, 7 and 8 parameters -> it uses 8 bind parameters (2^3)
- for 9, 10, 11, 12, 13, 14, 15 and 16 parameters -> it uses 16 parameters (2^4)
- ...

**Key points:**
- in `application.properties` set `spring.jpa.properties.hibernate.query.in_clause_parameter_padding=true`

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

