# Hibernate & Spring Boot (2.0.5)

**Hibernate & Spring Boot Samples**

1. **[Hibernate SpringBoot UTC Timezone And MySQL](https://github.com/AnghelLeonard/Hibernate/tree/master/HibernateSpringBootUTCTimezone)**

**Description:** How to store date, time, and timestamps in UTC time zone in MySQL

**Key points:**\
     - spring.jpa.properties.hibernate.jdbc.time_zone=UTC\
     - spring.datasource.url=jdbc:mysql://localhost:3306/db_screenshot **?useLegacyDatetimeCode=false**
     
-----------------------------------------------------------------------------------------------------------------------    

2. **[Hibernate SpringBoot View Binding Params Via Log4J 2](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLog4j2ViewBindingParameters)**

**Description:** View the prepared statement binding parameters via Log4J 2 logger setting

**Key points:**\
     - in pom.xml, exclude Spring Boot's Default Logging\
     - in pom.xml, Add Log4j 2 Dependency\
     - in log4j2.xml add, `<Logger name="org.hibernate.type.descriptor.sql" level="trace"/>`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLog4j2ViewBindingParameters/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

3. **[Hibernate SpringBoot View Query Details Via "datasource-proxy"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceProxy)**

**Description:** View the query details (query type, binding parameters, batch size, etc) via **[datasource-proxy](https://github.com/ttddyy/datasource-proxy)**

**Key points:**\
     - add in pom.xml the datasource-proxy dependency\
     - create an bean post processor to intercept the `DataSource` bean\
     - wrap the `DataSource` bean via `ProxyFactory` and an implementation of `MethodInterceptor`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceProxy/sample.png)
