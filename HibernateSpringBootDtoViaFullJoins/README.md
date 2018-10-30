**[How To Use Spring Projections(DTOs) And Full Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaFullJoins)**

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and right joins written via JPQL and native SQL (for PostgreSQL; MySQL does not support `FULL JOINS`).

**Key points:**\
     - define serveral entities (e.g., `Tournament` and `Player` in a bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-postgresql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `TournamentPlayerNameDto`)\
     - write right joins queries using JPQL/SQL, for example:\
     - Query all tournaments and players (`localhost:8080/allTournamentsAndPlayersFullJoinJpql`)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
