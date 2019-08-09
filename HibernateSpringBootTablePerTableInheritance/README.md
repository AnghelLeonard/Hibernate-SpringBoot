**[JPA Inheritance - Table-per-class](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTablePerTableInheritance)**

**Description:** This application is a sample of JPA Table-per-class inheritance strategy (`TABLE_PER_CLASS`)

**Key points:**\
     - this inheritance strategy doesn't allow the usage of the `IDENTITY` generator\
     - this inheritance strategy can be employed via `@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)`\
     - all the classes in an inheritance hierarchy (a.k.a., subclasses) are represented via individual tables\
     - each subclass-table stores the columns inherited from the superclass-table (*base class*)\

**Note:** This is the less efficient JPA inheritance strategy.

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
