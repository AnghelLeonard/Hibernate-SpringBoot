**[How To Calculate Non-Persistent Property via Hibernate `@Formula`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCalculatePropertyFormula)**
 
**Description:** This application is an example of calculating a non-persistent property of an entity based on the persistent entity attributes. In this case, we will use Hibernate, `@Formula`.

**Key points:**\
     - annotate the non-persistent property with `@Transient`\
     - annotate the non-persistent field with `@Formula`\
     - as the value of `@Formula` add the SQL query expression that calculates this non-persistent property based on the persistent entity attributes
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
