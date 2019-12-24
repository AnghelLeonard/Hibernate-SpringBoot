**[Defining Entity Listener Class Via `@EntityListeners`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEntityListener)**

**Description:** This application is a sample of using the JPA `@MappedSuperclass` and `@EntityListeners` with JPA callbacks.

**Key points:**
- the *base class* ,`Book`, is not an entity, it can be `abstract`, and is annotated with `@MappedSuperclass` and `@EntityListeners(BookListener.class)`
- `BookListener` defines JPA callbacks (e.g., `@PrePersist`)
- subclasses of the *base class* are mapped in tables that contains columns for the inherited attributes and for their own attibutes
- when any entity that is a subclass of `Book` is persisted, loaded, updated, etc the corresponding JPA callbacks are called

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

