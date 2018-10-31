**[How To Use Spring Projections(DTOs) And Right Excluding Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaRightExcludingJoins)**

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and right excluding joins written via JPQL and native SQL (we use MySQL).

**Key points:**\
     - define serveral entities (e.g., `Tournament` and `Player` in a bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-postgresql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `TournamentPlayerNameDto`)\
     - write right excluding joins queries using JPQL/SQL, for example:\
     - Query all players that are not in tournaments (`localhost:8080/allPlayersRightExcludingJoinJpql`)\
     - Query all tournaments that don't have players (`localhost:8080/allTournamentsRightExcludingJoinJpql`)
