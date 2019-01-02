
**[Attribute Lazy Loading](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyFetching)**

**Description:** By default, the attributes of an entity are loaded eager (all at once). We can load them **lazy** as well. This is useful for column types that store large amounts of data: `CLOB`, `BLOB`, `VARBINARY`, etc.

**Key points:**\
     - in pom.xml, activate Hibernate bytecode instrumentation (e.g. use Maven bytecode enhancement plugin as follows)\
     - mark the columns that should be loaded lazy with `@Basic(fetch = FetchType.LAZY)`
     
**Run the following requests:**\
     - create a new user: `localhost:8080/new`\
     - fetch the user without avatar (this is a picture, therefore a large amount of data): `localhost:8080/user`\
     - fetch the user with avatar (loaded lazy): `localhost:8080/avatar`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
