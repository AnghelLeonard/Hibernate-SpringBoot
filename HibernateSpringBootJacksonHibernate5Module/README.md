**[How To Avoid Lazy Initialization Issues Caused By Disabling Open Session In View Via Hibernate5Module](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJacksonHibernate5Module)**
 
**Note:** [Hibernate5Module](https://github.com/FasterXML/jackson-datatype-hibernate) is an *add-on module for Jackson JSON processor which handles Hibernate datatypes; and specifically aspects of lazy-loading*.
 
**Description:** By default, in Spring Boot, the Open Session in View anti-pattern is enabled. Now, imagine a lazy relationship (e.g., `@OneToMany`) between two entities, `Author` and `Book` (an author has associated more books). Next, a REST controller endpoint fetches an `Author` without the associated `Book`. But, the View (more precisely, Jackson), forces the lazy loading of the associated `Book` as well. Since OSIV will supply the already opened `Session`, the `Proxy` initializations take place successfully. 

Of course, the correct decision is to disable OSIV by setting it to `false`, but this will not stop Jackson to try to force the lazy initialization of the associated `Book` entities. Running the code again will result in an exception of type: *Could not write JSON: failed to lazily initialize a collection of role: com.bookstore.entity.Author.books, could not initialize proxy - no Session; nested exception is com.fasterxml.jackson.databind.JsonMappingException: failed to lazily initialize a collection of role: com.bookstore.entity.Author.books, could not initialize proxy - no Session*. 

Well, among the Hibernate5Module features we have support for dealing with this aspect of lazy loading and eliminate this exception. Even if OSIV will continue to be enabled (not recommended), Jackson will not use the `Session` opened via OSIV.

**Key points:**
- for Maven, add the Hibernate5Module dependency in `pom.xml`
- add a `@Bean` that returns an instance of `Hibernate5Module`
- annotate the `Author` bean with `@JsonInclude(Include.NON_EMPTY)` to exclude `null` or what is considered empty from the returned JSON
     
**Note:** The presence of Hibernate5Module instructs Jackson to initialize the lazy associations with default values (e.g., a lazy associated collection will be initialized with `null`). Hibernate5Module doesn't work for lazy loaded attributes. For such case consider [this](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingJacksonSerialization) item.

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

