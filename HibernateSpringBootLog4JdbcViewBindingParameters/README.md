
**[How To View Binding Params Via log4jdbc](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLog4JdbcViewBindingParameters)**

**Description:** View the prepared statement binding parameters via [log4jdbc](https://stackoverflow.com/questions/45346905/how-to-log-sql-queries-their-parameters-and-results-with-log4jdbc-in-spring-boo/45346996#45346996)

**Key points:**\
     - in pom.xml, Add `log4jdbc` Dependency\
     - in application.properties add: `logging.level.resultsettable=info, logging.level.sqltiming=info, logging.level.sqlonly=fatal, logging.level.audit=fatal, logging.level.resultset=fatal, logging.level.connection=fatal`   

