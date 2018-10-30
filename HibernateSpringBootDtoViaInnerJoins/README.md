**[How To Use Spring Projections(DTOs) And Inner Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaInnerJoins)**

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and inner joins written via JPQL and native SQL (for MySQL).

**Key points:**\
     - define serveral entities (e.g., `Tournament` and `Player` in a bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `TournamentPlayerNameDto`, `PlayerRankNameDto`, `TournamentIdNameDto`)\
     - write inner joins queries using JPQL/SQL, for example:\
     - Query the tournaments of all players (`localhost:8080/tournamentsOfPlayersNamesInnerJoinJpql`)\
     - Query all tournaments that have players with rank smaller or equal to "rank" (`localhost:8080/tournamentsIdNameByRankInnerJoinSql`)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
