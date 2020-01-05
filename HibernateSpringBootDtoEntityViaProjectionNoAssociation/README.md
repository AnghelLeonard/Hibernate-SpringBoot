**[Entity Inside Spring Projection (no association)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoEntityViaProjectionNoAssociation)**

<b><a href="https://persistencelayer.wixsite.com/springboot-hibernate/post/how-to-add-an-entity-inside-a-spring-projection-no-associations">If you prefer to read it as a blog-post containing the relevant snippets of code then check this post</a></b>

**Description:** If, for some reason, you need an entity in your Spring projection (DTO), then this application shows you how to do it via an example. In this case, there are two entities, `Author` and `Book`, that have no materialized association between them, but, they share the `genre` attribute. We use this attribute to join authors with books via JPQL. And, we want to fetch in a Spring projection the authors as entities, `Author`, and the `title` of the books.

**Key points:**
- define two unrelated entities (e.g., `Author` and `Book`)
- define the proper Spring projection having `public Author getAuthor()` and `public String getTitle()`
- write a JPQL to fetch data

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
