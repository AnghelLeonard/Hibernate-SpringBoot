**[Attribute Lazy Loading And Jackson Serialization](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingJacksonSerialization)**
 
**Description:** By default, the attributes of an entity are loaded eager (all at once). We can load them **lazy** as well. This is useful for column types that store large amounts of data: `CLOB`, `BLOB`, `VARBINARY`, etc or *details* that should be loaded on demand. In this application, we have an entity named `Author`. Its properties are: `id`, `name`, `genre`, `avatar` and `age`. And, we want to load `avatar` lazy. But, returning entities (as JSON) that contains un-fetched lazy loaded attributes from a rest controller (`@RestController`) will cause lazy initialization exceptions because Jackson tries to "force" the fetching of these attributes outside a session. 

**Key points:**\
     - in `pom.xml`, activate Hibernate *bytecode instrumentation* (e.g. use Maven *bytecode enhancement plugin*)\
     - annotate the columns that should be loaded lazy with `@Basic(fetch = FetchType.LAZY)`\
     - annotate the `Author` entity with `@JsonFilter("AuthorId")`\
     - disable Open Session in View\
     - create and configure this filter to be used by default via `SimpleBeanPropertyFilter.serializeAll()`\
     - at controller level (in the needed endpoint) replace the default filter with one as `SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "age", "genre")` and return `MappingJacksonValue`
     
**Run the following requests (via BookstoreController):**\         
     - lazy fetch the avatar of an author by id: `localhost:8080/author/avatar/{id}`\
     - fetch authors by age greater than or equal to the given age without avatar: `localhost:8080/authors/{age}`\
     - fetch authors by age greater than or equal to the given age with and avatar (but, don't do this, notice the N+1 issue caused by looping the list of authors and triggering SQLs for fetching the avatar of each author): `localhost:8080/authors/details/{age}`

**Check as well:**\
     - [Attribute Lazy Loading (basic)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAttributeLazyLoadingBasic)\
     - [Conditionally Loadind Lazy Attributes](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingWithConditionAndDefaults)

--------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
