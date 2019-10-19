**[Optimize `SELECT DISTINCT` Via Hibernate `HINT_PASS_DISTINCT_THROUGH` Hint](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHintPassDistinctThrough)**

**Description:** Starting with Hibernate 5.2.2, we can optimize JPQL (HQL) query entites of type `SELECT DISTINCT` via `HINT_PASS_DISTINCT_THROUGH` hint. Keep in mind that this hint is useful only for JPQL (HQL) query entites. Is not useful for scalar queries (queries that selects only one column or expression and returns one row) or DTO. In such cases, the `DISTINCT` JPQL keyword is needed to be passed to the underlying SQL query. This will instruct the database to remove duplicates from the result set.

**Key points:**\
     - use `@QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false"))`
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHintPassDistinctThrough/HINT_PASS_DISTINCT_THROUGH.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
