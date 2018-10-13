# Hibernate & Spring Boot

Hibernate & Spring Boot Samples

1. **[Hibernate SpringBoot UTC Timezone and MySQL](https://github.com/AnghelLeonard/Hibernate/tree/master/HibernateSpringBootUTCTimezone)**

**Description:** How to store date, time, and timestamps in UTC time zone in MySQL

**Key points:**\
     - spring.jpa.properties.hibernate.jdbc.time_zone=UTC\
     - spring.datasource.url=jdbc:mysql://localhost:3306/db_screenshot **?useLegacyDatetimeCode=false**
     
-----------------------------------------------------------------------------------------------------------------------    

2. **[Hibernate SpringBoot View Binding Params Via Log4J 2](https://github.com/AnghelLeonard/Hibernate/tree/master/HibernateSpringBootUTCTimezone)**

**Description:** View the PreparedStatement binding parameters via Log4J 2 logger setting

**Key points:**\
     - in pom.xml, exclude Spring Boot's Default Logging
     - in pom.xml, Add Log4j 2 Dependency
     - in log4j2.xml add, `<Logger name="org.hibernate.type.descriptor.sql" level="trace"/>`
   
-----------------------------------------------------------------------------------------------------------------------    
