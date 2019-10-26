**[Log Slow Queries Via DataSource-Proxy](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLogSlowQueries)**

**Description:** This application is a sample of logging only slow queries via **[DataSource-Proxy](https://github.com/ttddyy/datasource-proxy)**. A slow query is a query that has an exection time bigger than a specificed threshold in milliseconds.

**Key points:**
- for Maven, add in `pom.xml` the DataSource-Proxy dependency
- create an bean post processor to intercept the `DataSource` bean
- wrap the `DataSource` bean via `ProxyFactory` and an implementation of `MethodInterceptor`
- choose a threshold in milliseconds
- define a listener and override `afterQuery()`
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLogSlowQueries/log%20slow%20queries%20via%20datasource-proxy.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
