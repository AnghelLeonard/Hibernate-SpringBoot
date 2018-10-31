**[How To Use Spring Projections(DTOs) And Left Excluding Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaLeftExcludingJoins)**

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and left excluding joins written via JPQL and native SQL (we use MySQL).

**Key points:**\
     - define serveral entities (e.g., `Tournament` and `Player` in a bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-postgresql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `TournamentPlayerNameDto`)\
     - write left excluding joins queries using JPQL/SQL, for example:\
     - Query all players that are not in tournaments (`localhost:8080/allPlayersLeftExcludingJoinJpql`)\
     - Query all tournaments that don't have players (`localhost:8080/allTournamentsLeftExcludingJoinJpql`)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
