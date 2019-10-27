**[How To Define A Composite Primary Key Via `@IdClass`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCompositeKeyIdClass)**

**Description:** This application is a proof of concept of how to define a composite key via `@IdClass`. This application uses two entities, `Author` and `Book` involved in a lazy bidirectional `@OneToMany` association. The identifier of `Author` is composed by `name` and `age` via `AuthorId` class. The identifier of `Book` is just a typical auto-generated numeric value.

**Key points:**
- the composite key class (e.g., `AuthorId`) is `public`
- the composite key class must implement `Serializable`
- the composite key must define `equals()` and `hashCode()`
- the composite key must define a no-arguments constructor
     
**Note**: The `@IdClass` can be useful when we cannot modify the compsite key class. Otherwise, rely on [`@Embeddable`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCompositeKeyEmbeddable).
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
