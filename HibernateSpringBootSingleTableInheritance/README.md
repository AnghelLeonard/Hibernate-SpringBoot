**[JPA Inheritance - Single Table](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSingleTableInheritance)**

**Description:** This application is a sample of JPA Single Table inheritance strategy (`SINGLE_TABLE`)

**Key points:**\
     - this is the default inheritance strategy (`@Inheritance(strategy=InheritanceType.SINGLE_TABLE)`)\
     - all the classes in a hierarchy are mapped to a single table in a the database\
     - subclasses attributes non-nullability is ensured via `@NotNull` and MySQL triggers\
     - the default discriminator column memory footprint was optimized by declaring it of type `TINYINT`
   
**Output example (below is a single table obtained from 3 entities):**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSingleTableInheritance/Single%20table%20inheritance.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
