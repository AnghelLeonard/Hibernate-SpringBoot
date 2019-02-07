**[How To Map An Entity To a Query (@Subselect) in a Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSubselect)**

**Note:** Consider using `@Subselect` only if using DTO + extra queries or map a database view to an entity is not a solution.

**Description:** This application is an example of mapping an entity to a query via Hibernate, `@Subselect`. Mainly, we have two entities in a bidirectional *one-to-many* association. A `Tournament` has multiple `Player`. The idea is to write a *read-only* query to fetch from `Tournament` only some fields (e.g., DTO), but to have the posibility to call `getPlayers()` and fetch the `Player` in a lazy manner as well. As you know, a classic DTO cannot be used, since such DTO is not managed and we cannot fetch managed associations. Via Hibernate, `@Subselect` we can map an entity to a query to obtain a *read-only*, *immutable* entity that can follow managed associations. 

**Key points:**\
     - define a new entity that contains only the needed fields from the `Tournament` (including association to `Player`)\
     - for these fields, define only getters\
     - mark the entity as `@Immutable` since no write operations are allowed\
     - flush pending state transitions for the used entities by `@Synchronize`\
     - use `@Subselect` to write the needed query, map an entity to an SQL query

