**[How To Store java.time.YearMonth As Integer Or Date Via Hibernate Types](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootYearMonth)**

**Description:** (Hibernate Types)[https://github.com/vladmihalcea/hibernate-types] is a set of extra types not supported by Hibernate by default. One of these types is `java.time.YearMonth`. This is a Spring Boot application that uses Hibernate Type to store this `YearMonth` in a MySQL database as integer or `Date`.

**Key points:**\
     - for Maven, add Hibernate Types as a dependency in pom.xml\
     - in entity use `@TypeDef` to map `typeClass` to `defaultForType` 
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootYearMonth/sample.png)  
