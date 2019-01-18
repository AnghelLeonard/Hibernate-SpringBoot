
**[How To Use Hibernate @NaturalId in SpringBoot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNaturalId)**

**Description:** This is a SpringBoot application that maps a natural business key using Hibernate `@NaturalId`.

**Key points:**\
     - in the entity (e.g., `Product`), mark the properties (business keys) that should act as natural IDs with `@NaturalId`; commonly, there is a single such property, but multiple are suppored as well as [here](https://docs.jboss.org/hibernate/orm/5.0/mappingGuide/en-US/html/ch07.html).\
     - for non-mutable ids, mark the columns as `@NaturalId(mutable = false)` and `@Column(nullable = false, updatable = false, unique = true, ...)`\
     - for mutable ids, mark the columns as `@NaturalId(mutable = true)` and `@Column(nullable = false, updatable = true, unique = true, ...)`\
     - override the `equals()` and `hashCode()` using the natural id(s)\
     - define a `@NoRepositoryBean` interface (`NaturalRepository`) to define two methods, named `findBySimpleNaturalId()` and `findByNaturalId()`\
     - provide an implementation for this interface (`NaturalRepositoryImpl`) relying on Hibernate, `Session`, `bySimpleNaturalId()` and `byNaturalId()` methods\
     - for the entity, write a repository class (e.g., for the `Product` entity write `ProductNaturalRepository`) that extends the `NaturalRepositoryImpl` and use it for setting the entity class type and the natural id type\
     - inject this class in your services and call `findBySimpleNaturalId()` or `findByNaturalId()`
