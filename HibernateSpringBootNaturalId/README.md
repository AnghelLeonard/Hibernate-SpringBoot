
**[How To Use Hibernate `@NaturalId` in SpringBoot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNaturalId)**

**Description:** This is a SpringBoot application that maps a natural business key using Hibernate `@NaturalId`.

**Key points:**
- in the entity (e.g., `Book`), mark the fields (business keys) that should act as natural IDs with `@NaturalId`; commonly, there is a single such property, but multiple are suppored as well as [here](https://docs.jboss.org/hibernate/orm/5.0/mappingGuide/en-US/html/ch07.html).
- for non-mutable ids, mark the columns as `@NaturalId(mutable = false)` and `@Column(nullable = false, updatable = false, unique = true, ...)`
- for mutable ids, mark the columns as `@NaturalId(mutable = true)` and `@Column(nullable = false, updatable = true, unique = true, ...)`
- override the `equals()` and `hashCode()` using the natural id(s)
- define a `@NoRepositoryBean` interface (`NaturalRepository`) to define two methods, named `findBySimpleNaturalId()` and `findByNaturalId()`
- provide an implementation for this interface (`NaturalRepositoryImpl`) relying on Hibernate, `Session`, `bySimpleNaturalId()` and `byNaturalId()` methods
- for the entity, write a repository class (e.g., for the `Product` entity write `ProductNaturalRepository`) that extends the `NaturalRepositoryImpl` and use it for setting the entity class type and the natural id type (when an entity uses more than one natural ID, the type is not relevant anymore, simply set it to `Serializable`)
- inject this class in your services and call `findBySimpleNaturalId()` or `findByNaturalId()`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
