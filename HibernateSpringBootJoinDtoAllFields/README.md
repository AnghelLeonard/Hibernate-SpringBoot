**[Fetching All Entity Attributes As Spring Projection (DTO)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinDtoAllFields)**
 
**Description:** This application is a sample of fetching all attributes of an entity (`Author`) as a Spring projection (DTO). Commonly, a DTO contains a subset of attributes, but, sometimes we need to fetch the whole entity as a DTO. In such cases, we have to pay attention on how we write the query. Some queries can bring the result set in Persistent Context as well, and this is a performance penalty.

**Key points:**
- fetching the result set as a `List<Object[]>` or `List<AuthorDto>` via a JPQL of type `SELECT a FROM Author a` **WILL** fetch the result set as entities in Persistent Context as well - avoid this approach
- fetching the result set as a `List<Object[]>` or `List<AuthorDto>` via a JPQL of type `SELECT a.id AS id, a.name AS name, ... FROM Author a` will **NOT** fetch the result set in Persistent Context
- fetching the result set as a `List<Object[]>` or `List<AuthorDto>` via a native SQL of type `SELECT * FROM author` will **NOT** fetch the result set in Persistent Context
- fetching the result set as a `List<Object[]>` via Spring Data query builder mechanism **WILL** fetch the result set in Persistent Context - avoid this approach
- fetching the result set as a `List<AuthorDto>` via Spring Data query builder mechanism will **NOT** fetch the result set in Persistent Context

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
