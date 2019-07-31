
**[How To Use Entity Graphs (`@NamedEntityGraph`) In Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedEntityGraph)**

**Note:** In a nutshell, *entity graphs* (aka, *fetch plans*) is a feature introduced in JPA 2.1 that help us to improve the performance of loading entities. Mainly, we specify the entity’s related associations and basic fields that should be loaded in a single `SELECT` statement. We can define multiple *entity graphs* for the same entity and *chain* any number of entities and even use *sub-graphs* to create complex *fetch plans*. To override the current `FetchType` semantics there are properties that can be set:

*Fetch Graph* (default), `javax.persistence.fetchgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated as `FetchType.LAZY` regardless of the default/explicit `FetchType`.

*Load Graph*, `javax.persistence.loadgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated according to their specified or default `FetchType`.

**Nevertheless, the JPA specs doesn't apply in Hibernate for the basic (`@Basic`) attributes.**. More details [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedEntityGraphBasicAttrs).

**Description:** This is a sample application of using *entity graphs* in Spring Boot.

**Key points:**\
     - define two entities, `Author` and `Book`, involved in a lazy bidirectional `@OneToMany` relationship\
     - in `Author` entity use the `@NamedEntityGraph` to define the *entity graph* (e.g., load eagerly the authors and the associatated books)\
     - in `AuthorRepositry` rely on Spring `@EntityGraph` annotation to indicate the *entity graph* defined at the previous step

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
