**[Entity Inside Spring Projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoEntityViaProjection)**
     
**Description:** If, for any reason, you need an entity in your Spring projection (DTO), then this application shows you how to do it via an example. In this case, there are two entities, `Author` and `Book`, involved in a lazy bidirectional one-to-many association  (it can be other association as well, or even no materialized association). And, we want to fetch in a Spring projection the authors as entities, `Author`, and the `title` of the books.

**Key points:**\
     - define two related entities (e.g., `Author` and `Book` in a one-to-many lazy bidirectional relationship)\
     - define the proper Spring projection having `public Author getAuthor()` and `public String getTitle()`\     
     - write a JPQL to fetch data

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
