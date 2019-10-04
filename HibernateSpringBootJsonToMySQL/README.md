**[How To Handle JSON in MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJsonToMySQL)**

**Description:** [Hibernate Types](https://github.com/vladmihalcea/hibernate-types) is a library of extra types not supported by Hibernate Core by default. This is a Spring Boot application that uses this library to persist JSON data (JSON Java `Object`) in a MySQL `json` column and for querying JSON data from the MySQL `json` column to JSON Java `Object`. Updates are supported as well.

**Key points:**\
     - for Maven, add Hibernate Types as a dependency in pom.xml\
     - in entity use `@TypeDef` to map `typeClass` to `JsonStringType`

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
