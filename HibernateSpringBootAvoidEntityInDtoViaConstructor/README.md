**[Avoid Entity In DTO Via Constructor Expression (no association)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAvoidEntityInDtoViaConstructor)**

<b><a href="https://persistencelayer.wixsite.com/springboot-hibernate/post/avoid-fetching-entity-in-dto-via-constructor-expression-no-associations">If you prefer to read it as a blog-post containing the relevant snippets of code then check this post</a></b>

**Description:** Let's assume that we have two entities, `Author` and `Book`. There is no materialized association between them, but, both entities shares an attribute named, `genre`. We want to use this attribute to join the tables corresponding to `Author` and `Book`, and fetch the result in a DTO. The result should contain the `Author` entity and only the `title` attribute from `Book`. Well, when you are in a scenario as here, it is strongly advisable to avoid fetching the DTO via *constructor expression*. This approach cannot fetch the data in a single `SELECT`, and is prone to N+1. Way better than this consists of using Spring projections, JPA `Tuple` or even Hibernate `ResultTransformer`. These approaches will fetch the data in a single `SELECT`. This application is a **DON'T DO THIS** example. Check the number of queries needed for fetching the data. In place, do it as here: [Entity Inside Spring Projection (no association)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoEntityViaProjectionNoAssociation).

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
