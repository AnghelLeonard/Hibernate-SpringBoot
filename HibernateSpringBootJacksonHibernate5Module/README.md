**[How To Avoid Lazy Initialization Issues Caused By Disabling Open Session In View Via Hibernate5Module](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJacksonHibernate5Module)**

**Note:** [Hibernate5Module](https://github.com/FasterXML/jackson-datatype-hibernate) is an *add-on module for Jackson JSON processor which handles Hibernate datatypes; and specifically aspects of lazy-loading*
 
**Description:** By default, in Spring Boot, the Open Session In View anti-pattern is enabled. Now, imagine a lazy relationship (e.g., `@OneToMany`) between two entities, `Author` and `Book` (an author has associated more books). Next, a REST controller endpoint fetches an `Author` whithout the associated `Book`. But, the View (Jackson), forces the lazy loading of the associated `Book` as well. This is working since OSIV will supply the current open `Session`. Of course, the correct decision is to disable OSIV by setting it to `false`, but this will not stop Jackson to try to force the lazy initialization of the associated `Book` entities. Running the code again will result in an exception of type: *Could not write JSON: failed to lazily initialize a collection of role: com.bookstore.entity.Author.books, could not initialize proxy - no Session; nested exception is com.fasterxml.jackson.databind.JsonMappingException: failed to lazily initialize a collection of role: com.bookstore.entity.Author.books, could not initialize proxy - no Session*. Well, among the Hibernate5Module features we have support for dealing with this aspect of lazy loading and eliminate this exception. And, if you really need OSIV, then you can enable it since Hibernate5Module will prevent OSIV to trigger lazy loading the associations.

**Key points:**\
     - add the Hibernate5Module dependency in `pom.xml`\
     - add a `@Bean` that returns an instance of `Hibernate5Module`\
     - annotate the `Author` bean with `@JsonInclude(Include.NON_EMPTY)` to exclude nulls from the returned JSON

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
