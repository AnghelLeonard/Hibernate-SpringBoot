**[How To Enrich DTO With Virtual Properties Via Spring Projections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjectionsAndVirtualProperties)**

**Note:** You may also like to read the recipe, ["How To Create DTO Via Spring Data Projections"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)

**Description:** This is an application sample that fetches only the needed columns from the database via Spring Data Projections (DTO) and enrich the result via virtual properties.

**Key points:**
- we fetch from the database only the author `name` and `age`
- in the projection interface, `AuthorNameAge`, use the `@Value` and Spring SpEL to point to a backing property from the domain model (in this case, the domain model property `age` is exposed via the virtual property `years`)
- in the projection interface, `AuthorNameAge`, use the `@Value` and Spring SpEL to enrich the result with two virtual properties that don't have a match in the domain model (in this case, `rank` and `books`)

**Output example:**\
<a href="#"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjectionsAndVirtualProperties/dto%20spring%20projection%20and%20virtual%20properties.png" align="center" height="220" width="444" ></a>

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

