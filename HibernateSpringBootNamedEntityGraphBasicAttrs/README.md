
**[How To Use Entity Graphs For `@Basic` Attributes In Hibernate And Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedEntityGraphBasicAttrs)**

**Note:** In a nutshell, *entity graphs* (aka, *fetch plans*) is a feature introduced in JPA 2.1 that help us to improve the performance of loading entities. Mainly, we specify the entity’s related associations and basic fields that should be loaded in a single `SELECT` statement. We can define multiple *entity graphs* for the same entity and *chain* any number of entities and even use *sub-graphs* to create complex *fetch plans*. To override the current `FetchType` semantics there are properties that can be set:

*Fetch Graph* (default), `javax.persistence.fetchgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated as `FetchType.LAZY` regardless of the default/explicit `FetchType`.

*Load Graph*, `javax.persistence.loadgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated according to their specified or default `FetchType`.

**Nevertheless, the JPA specs doesn't apply in Hibernate for the basic (`@Basic`) attributes.** In other words, by default, attributes are annotated with `@Basic` which rely on the default fetch policy. The default fetch policy is `FetchType.EAGER`. These attributes are also loaded in case of *fetch graph* even if they are not explicitly specified via `@NamedAttributeNode`. Annotating the basic attributes that should not be fetched with `@Basic(fetch = FetchType.LAZY)` it is not enough. Both, *fetch graph* and *load graph* will ignore these settings as long as we don't add *bytecode enhancement* as well.

**The main drawback consists of the fact the these basic attributes are fetched `LAZY` by all other queries (e.g., `findById()`) not only by the queries using the entity graph, and most probably, you will not want this behavior.**

**Description:** This is a sample application of using *entity graphs* with `@Basic` attributes in Spring Boot.

**Key points:**
- define two entities, `Author` and `Book`, involved in a lazy bidirectional `@OneToMany` association
- in `Author` entity use the `@NamedEntityGraph` to define the *entity graph* (e.g., load the authors names (only the `name` basic attribute; ignore the rest) and the associatated books)
- add *bytecode enhancement*
- annotate the basic attributes that should be ignored by the *entity graph* with `@Basic(fetch = FetchType.LAZY)`
- in `AuthorRepository` rely on Spring `@EntityGraph` annotation to indicate the *entity graph* defined at the previous step

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
