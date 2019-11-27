**[Log Slow Queries Via DataSource-Proxy](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLogSlowQueries)**

**Description:** This application is a sample of logging only slow queries via **[DataSource-Proxy](https://github.com/ttddyy/datasource-proxy)**. A slow query is a query that has an execution time bigger than a specificed threshold in milliseconds.

**Key points:**
- for Maven, add in `pom.xml` the DataSource-Proxy dependency
- create an bean post processor to intercept the `DataSource` bean
- wrap the `DataSource` bean via `ProxyFactory` and an implementation of `MethodInterceptor`
- choose a threshold in milliseconds
- define a listener and override `afterQuery()`
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLogSlowQueries/log%20slow%20queries%20via%20datasource-proxy.png)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
