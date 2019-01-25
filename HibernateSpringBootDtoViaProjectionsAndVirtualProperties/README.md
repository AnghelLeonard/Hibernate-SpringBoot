**[How To Enrich DTOs With Virtual Properties Via Spring Projections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjectionsAndVirtualProperties)**

**Note:** You may also like to read the recipe, ["How To Create DTOs Via Spring Data Projections"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)

**Description:** Fetch only the needed data from the database via Spring Data Projections (DTOs) and enrich the result via virtual properties.

**Key points:**\
     - we fetch from the database only the user `name` and `city`\
     - in the projection interface, `UserDetail`, use the `@Value` and Spring SpEL to point to a backing property from the domain model (in this case, the domain model property `city` is exposed via the virtual property `livingin`)\
     - in the projection interface, `UserDetail`, use the `@Value` and Spring SpEL to enrich the result with two virtual properties that don't have a match in the domain model (in this case, `sessionid` and `status`)

**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjectionsAndVirtualProperties/dto%20spring%20projection%20and%20virtual%20properties.png)

------------------------------------------------------

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
