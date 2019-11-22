**[How To Fetch DTO Via A Custom `ResultTransformer`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoCustomResultTransformer)**
  
**Description:** Fetching more *read-only* data than needed is prone to performance penalties. Using DTO allows us to extract only the needed data. Sometimes, we need to fetch a DTO made of a subset of properties (columns) from a parent-child association. For such cases, we can use SQL `JOIN` that can pick up the desired columns from the involved tables. But, `JOIN` returns an `List<Object[]>` and most probably you will need to represent it as a `List<ParentDto>`, where a `ParentDto` instance has a `List<ChildDto>`. For such cases, we can rely on a custom Hibernate `ResultTransformer`. This application is a sample of writing a custom `ResultTransformer`.

**Key points:**
- implement the `ResultTransformer` interface

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
