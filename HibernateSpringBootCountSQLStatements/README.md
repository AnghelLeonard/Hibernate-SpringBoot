**[Count and Assert SQL Statements](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCountSQLStatements)**

**Description:** This application is a sample of counting and asserting SQL statements triggered "behind the scene". Is very useful to count the SQL statements in order to ensure that your code is not generating more SQL statements that you may think (e.g., N+1 can be easily detected by asserting the number of expected statements).

**Key points:**
- for Maven, in `pom.xml`, add dependencies for DataSource-Proxy library and Vlad Mihalcea's db-util library
- create the `ProxyDataSourceBuilder` with `countQuery()`
- reset the counter via `SQLStatementCountValidator.reset()`
- assert `INSERT`, `UPDATE`, `DELETE` and `SELECT` via `assertInsert/Update/Delete/Select/Count(long expectedNumberOfSql)`
   
**Output example (when the number of expected SQLs is not equal with the reality an exception is thrown):**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootCountSQLStatements/count%20and%20assert%20SQL.png)

---------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
