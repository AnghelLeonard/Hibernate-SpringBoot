**[Fetch Child Entities Of @ManyToMany Via Ordered Set](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManySetAndOrderBy)**

**Description:** In case of `@ManyToMany` association, we always should rely on `Set` (not on `List`) for mapping the child entities as a collection. Why? Well, please see [Prefer Set Instead of List in @ManyToMany Relationships](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManyBidirectionalListVsSet). But, is well-known that `HashSet` doesn't preserve the order of elements. This application relies on `@OrderBy` for sorting the result set in memory, after feching it from the database.

This application uses two entities, `Author` and `Book`, involved in a lazy bidirectional many-to-many relationship. First, we fetch a `Book` by title. Further, we call `getAuthors()` to fetch the authors of this book. The fetched authors are mapped in a `HashSet` that is ordered descending by name via `@OrderBy("name DESC")`.

**Key points:**\
     - sorting the result set in memory can be done via `@OrderBy`\
     - this works with `HashSet`, but doesn't provide consistency over all transition states (e.g., over transient state)\
     - for consistency over transient state as well, consider `LinkedHashSet` instead of `HashSet`

**Note:** Alternatively, we can use `@OrderColumn`. This gets materialized in an additional column in the junction table. This is needed for maintaining a permanent ordering of the related data.

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
