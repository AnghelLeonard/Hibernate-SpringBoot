
**[Attribute Lazy Loading](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAttributeLazyLoadingBasic)**
 
**Description:** By default, the attributes of an entity are loaded eager (all at once). We can load them **lazy** as well. This is useful for column types that store large amounts of data: `CLOB`, `BLOB`, `VARBINARY`, etc or *details* that should be loaded on demand. In this application, we have an entity named `Author`. Its properties are: `id`, `name`, `genre`, `avatar` and `age`. And, we want to load `avatar` and `age` lazy. We consider `age` and `avatar` as author's *details* that should be loaded on demand.

**Key points:**\
     - in `pom.xml`, activate Hibernate *bytecode instrumentation* (e.g. use Maven *bytecode enhancement plugin*)\
     - annotate the columns that should be loaded lazy with `@Basic(fetch = FetchType.LAZY)`\
     - disable Open Session in View     

**Check as well:**\
     - [Conditionally Loadind Lazy Attributes](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingWithConditionAndDefaults)\
     - [Attribute Lazy Loading And Jackson Serialization](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingJacksonSerialization)
     
-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
