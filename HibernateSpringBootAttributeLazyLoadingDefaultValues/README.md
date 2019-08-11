
**[Default Values For Lazy Loaded Attributes](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingDefaultValues)**
 
**Description:** By default, the attributes of an entity are loaded eagerly (all at once). But, we can load them **lazy** as well. This is useful for column types that store large amounts of data: `CLOB`, `BLOB`, `VARBINARY`, etc or *details* that should be loaded on demand. In this application, we have an entity named `Author`. Its properties are: `id`, `name`, `genre`, `avatar` and `age`. And, we want to load the `avatar` lazy. If the fetched author is younger than 40 years, we will load the `avatar` as well. Otherwise, we explicitly set the avatar as `null` (this is like the default value). 

**Key points:**\
     - in `pom.xml`, activate Hibernate *bytecode instrumentation* (e.g. use Maven *bytecode enhancement plugin*)\
     - in entity, annotate the columns that should be loaded lazy with `@Basic(fetch = FetchType.LAZY)`\
     - annotate the `Author` entity with `@JsonInclude(Include.NON_EMPTY)` to avoid the serialization of fields with empty values (e.g., useful when we set `avatar` to `null`)\
     - in `application.properties`, disable Open Session in View
     
**Run the following requests (via BookstoreController):**\
     - fetch an author with the avatar set to `null`: `localhost:8080/author/1`\
     - fetch an author with the avatar lazy loaded: `localhost:8080/author/2`

**Check as well:**\
     - [Attribute Lazy Loading (basic)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAttributeLazyLoadingBasic)\
     - [Attribute Lazy Loading And Jackson Serialization](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingJacksonSerialization)
     
--------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>

