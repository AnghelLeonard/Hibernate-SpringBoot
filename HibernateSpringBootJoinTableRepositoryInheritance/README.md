**[JPA Inheritance - `JOINED`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinTableInheritance)**

**Description:** This application is a sample of JPA Join Table inheritance strategy (`JOINED`)

**Key points:**
- this inheritance strategy can be employed via `@Inheritance(strategy=InheritanceType.JOINED)`
- all the classes in an inheritance hierarchy (a.k.a., subclasses) are represented via individual tables
- by default, subclass-tables contains a primary key column that acts as a foreign key  as well - this foreign key references the *base class* table primary key
- customizing this foreign key can be done by annotating the subclasses with `@PrimaryKeyJoinColumn`  

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
