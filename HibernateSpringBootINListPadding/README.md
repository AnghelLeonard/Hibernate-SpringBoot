**[`IN` Clause Parameter Padding](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootINListPadding)**
 
**Description:** This application is an example of using Hibernate `IN` cluase parameter padding. This way we can reduce the number of Execution Plans. Mainly, Hibernate is padding parameters as follows: 

- for 3 and 4 parameters -> it uses 4 bind parameters
- for 5, 6, 7 and 8 parameters -> it uses 8 bind parameters
- for 9, 10, 11, 12, 13, 14, 15 and 16 parameters -> it uses 16 parameters
- ...

**Key points:**
- in `application.properties` set `spring.jpa.properties.hibernate.query.in_clause_parameter_padding=true`

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
