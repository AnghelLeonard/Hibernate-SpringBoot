**[How To Fetch DTO Via JDK14 Records And A Custom `ResultTransformer`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoRecordResultTransformer)**
  
**Description:** Fetching more *read-only* data than needed is prone to performance penalties. Using DTO allows us to extract only the needed data. Sometimes, we need to fetch a DTO made of a subset of properties (columns) from a parent-child association. For such cases, we can use SQL `JOIN` that can pick up the desired columns from the involved tables. But, `JOIN` returns an `List<Object[]>` and most probably you will need to represent it as a `List<ParentDto>`, where a `ParentDto` instance has a `List<ChildDto>`. For such cases, we can rely on a custom Hibernate `ResultTransformer`. This application is a sample of writing a custom `ResultTransformer`.

As DTO, we rely on JDK 14 Records. From Openjdk JEP359:

*Records provide a compact syntax for declaring classes which are transparent holders for shallowly immutable data.*

**Key points:**
- define the Java Records as `AuthorDto` and `BookDto`
- implement the `ResultTransformer` interface

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

