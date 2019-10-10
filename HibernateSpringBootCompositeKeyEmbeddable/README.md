**[How To Define A Composite Primary Key Via `@Embeddable`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCompositeKeyEmbeddable)**

**Description:** This application is a proof of concept of how to define a composite key via `@Embeddable` and `@EmbeddedId`. This application uses two entities, `Author` and `Book` involved in a lazy bidirectional `@OneToMany` relationship. The identifier of `Author` is composed by `name` and `age` via `AuthorId` class. The identifier of `Book` is just a typical auto-generated numeric value.

**Key points:**\
     - The composite key class (e.g., `AuthorId`) is `public`\
     - The composite key class must implement `Serializable`\
     - The composite key must define `equals()` and `hashCode()`\
     - The composite key must define a no-arguments constructor
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
