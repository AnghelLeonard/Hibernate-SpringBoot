**[JPA Inheritance - Join Table](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinTableInheritance)**

**Description:** This application is a sample of JPA Join Table inheritance strategy (`JOINED`)

**Key points:**\
     - this inheritance strategy can be employed via `@Inheritance(strategy=InheritanceType.JOINED)`\
     - all the classes in an inheritance hierarchy (a.k.a., subclasses) are represented via individual tables\
     - by default, subclass-tables contains a primary key column that acts as a foreign key  as well - this foreign key references the *base class* table primary key\
     - customizing this foreign key can be done by annotating the subclasses with `@PrimaryKeyJoinColumn.`  

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
