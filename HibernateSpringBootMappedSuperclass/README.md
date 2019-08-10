**[JPA Inheritance - `@MappedSuperclass`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMappedSuperclass)**

**Description:** This application is a sample of using the JPA `@MappedSuperclass`.

**Key points:**\
     - the *base class* is not an entity, it can be `abstract`, and is annotated with `@MappedSuperclass`\
     - subclasses of the *base class* are mapped in tables that contains columns for the inherited attributes and for their own attibutes\
     - when the *base class* doens't need to be an entity, the `@MappedSuperclass` is the proper alternative to the JPA table-per-class inheritance strategy

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
