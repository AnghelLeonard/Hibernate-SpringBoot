**[How To Provide a Fluent API Via Additional Methods For Building Entities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiAdditionalMethods)**

**Note**: If you want yo provide a Fluent API by altering setters then consider this recipe.

**Description:** This is a sample application that add in entities additional methods (e.g., for `setName`, we add `name`) methods in order to empower a Fluent API.

**Key points:**\
     - in entities, add for each setter an additional method that return `this` instead of `void`

**Fluent API example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFluentApiAdditionalMethods/sample.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
