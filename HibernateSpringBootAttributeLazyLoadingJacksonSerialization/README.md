**[Attribute Lazy Loading And Jackson Serialization](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingJacksonSerialization)**
 
<b><a href="https://persistencelayer.wixsite.com/springboot-hibernate/post/attribute-lazy-loading-and-jackson-serialization">If you prefer to read it as a blog-post containing the relevant snippets of code then check this post</a></b>
 
**Description:** By default, the attributes of an entity are loaded eagerly (all at once). But, we can load them **lazily** as well. This is useful for column types that store large amounts of data: `CLOB`, `BLOB`, `VARBINARY`, etc or *details* that should be loaded on demand. In this application, we have an entity named `Author`. Its properties are: `id`, `name`, `genre`, `avatar` and `age`. And, we want to load the `avatar` lazy. But, returning entities (as JSON) that contains un-fetched lazy attributes from a REST controller (`@RestController`) will cause lazy initialization exceptions because Jackson tries to force the fetching of these attributes outside a Hibernate session. 

**Key points:**
- for Maven, in `pom.xml`, activate Hibernate *bytecode enhancement* (e.g. use Maven *bytecode enhancement plugin*)
- in entity, annotate the attributes that should be loaded lazy with `@Basic(fetch = FetchType.LAZY)`
- in `application.properties`, disable Open Session in View
- annotate the `Author` entity with `@JsonFilter("AuthorId")`
- create and configure the `AuthorId` filter to be used by default via `SimpleBeanPropertyFilter.serializeAll()`
- at controller level (in the needed endpoint) override the `AuthorId` filter with one as `SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "age", "genre")` and return `MappingJacksonValue`
- a controller endpoint that need to use the overriden version of `AuthorId` filter should rely on `MappingJacksonValue`
     
**Run the following requests (via BookstoreController):**
- lazy fetch the avatar of an author by id: `localhost:8080/author/avatar/{id}`
- fetch authors by age greater than or equal to the given age without avatar: `localhost:8080/authors/{age}`
- fetch authors by age greater than or equal to the given age with and avatar (but, don't do this, notice the N+1 issue caused by looping the list of authors and triggering SQLs for fetching the avatar of each author): `localhost:8080/authors/details/{age}`

**Check as well:**
- [Attribute Lazy Loading (basic)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAttributeLazyLoadingBasic)
- [Default Values For Lazy Loaded Attributes](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingDefaultValues)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

