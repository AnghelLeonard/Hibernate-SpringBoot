
**[Conditionally Loadind Lazy Attributes](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingWithConditionAndDefaults)**
 
**Description:** By default, the attributes of an entity are loaded eagerly (all at once). But, we can load them **lazy** as well. This is useful for column types that store large amounts of data: `CLOB`, `BLOB`, `VARBINARY`, etc or *details* that should be loaded on demand. In this application, we have an entity named `Author`. Its properties are: `id`, `name`, `genre`, `avatar` and `age`. And, we want to load the `avatar` lazy. Actually, we loop the list of all authors, and, for those younger than 25 years, we will load the `avatar` as well. For the rest of authors, we explicitly set the avatar as `null` (this is like the default value). Pay attention that this technique is prone to N+1 issue. The more authors youger than 25, the more N is added to 1.

**Key points:**\
     - in `pom.xml`, activate Hibernate *bytecode instrumentation* (e.g. use Maven *bytecode enhancement plugin*)\
     - in entity, annotate the columns that should be loaded lazy with `@Basic(fetch = FetchType.LAZY)`\
     - annotate the `Author` entity with `@JsonInclude(Include.NON_DEFAULT)` to avoid the serialization of fields with default values (e.g., useful when we set `avatar` to `null`)\
     - in `application.properties`, disable Open Session in View
     
**Run the following requests (via BookstoreController):**\    
     - fetch all authors (for those younger than 25 years the avatar is lazy loaded): `localhost:8080/authors`

**Check as well:**\
     - [Attribute Lazy Loading (basic)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAttributeLazyLoadingBasic)\
     - [Attribute Lazy Loading And Jackson Serialization](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingJacksonSerialization)
     
--------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>

