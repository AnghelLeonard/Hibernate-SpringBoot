**[How To Define A Relationship in an `@Embeddable` Composite Primary Key](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCompositeKeyEmbeddableMapRel)**

**Description:** This application is a proof of concept of how to define a relationship in an `@Embeddable` composite key. The composite key is `AuthorId` and it belongs to the `Author` class.

**Key points:**\
     - The composite key class (e.g., `AuthorId`) is `public`\
     - The composite key class must implement `Serializable`\
     - The composite key must define `equals()` and `hashCode()`\
     - The composite key must define a no-arguments constructor
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
