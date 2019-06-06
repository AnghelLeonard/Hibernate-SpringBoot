
**[Attributes Lazy Loading Via Subentities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSubentities)**
 
**Description:** By default, the attributes of an entity are loaded eager (all at once). This application is an alternative to *Attribute Lazy Loading* from [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingBasic). This application uses a base class to isolate the attributes that should be loaded eagerly and subentities (entities that extends the base class) for isolating the attributes that should be loaded on demand.

<a href="#"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSubentities/attributes%20lazy%20loading%20via%20subentites.png" align="center" height="150" width="500" ></a>

**Key points:**\
     - create the base class (this is not an entity), `BaseAuthor`,  and annotate it with `@MappedSuperclass`\
     - create `AuthorShallow` subentity of `BaseAuthor` and don't add any attribute in it (this will inherit the attributes from the superclass)\
     - create `AuthorDeep` subentity of `BaseAuthor` and add to it the attributes that should be loaded on demand (e.g., `avatar`)\
     - map both subentities to the same table via `@Table(name = "author")`\
     - provide the typical repositories, `AuthorShallowRepository` and `AuthorDeepRepository`
     
**Run the following requests (via BookstoreController):**\
     - fetch all authors shallow (without avatars): `localhost:8080/authors/shallow`\
     - fetch all authors deep (with avatars): `localhost:8080/authors/deep`

**Check as well:**\
     - [Attribute Lazy Loading (basic)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAttributeLazyLoadingBasic)\
     - [Conditionally Loadind Lazy Attributes](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingWithConditionAndDefaults)\
     - [Attribute Lazy Loading And Jackson Serialization](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingJacksonSerialization)
     
--------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>

