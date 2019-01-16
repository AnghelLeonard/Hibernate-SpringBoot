**[How To Batch Deletes In MySQL (no relationships)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteSingleEntity)**

**Description:** Batch deletes in MySQL having a single entity class (no relationships).

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use batching. The first one simply triggers a `delete from entity_name` statement, while the second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement.

**Key points:**\
     - rely on `SimpleJpaRepository#delete()` method\
     - in application.properties set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in application.properties set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL, statements get rewritten into a single `String` buffer and sent in a single request)\
     - in application.properties set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in application.properties set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\     
     - before Hibernate 5, we need to set in application.properties a setting for enabling batching for versioned entities during update
and delete operations (entities that contains `@Version` for implicit optimistic locking). This setting is: `spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true`. Starting with Hibernate 5, this setting should be `true` by default.
   
**Output example:**

**FIRST APPROACH: deleteAllInBatch()**\
Output sample - **no batching**:\
Name:DATA_SOURCE_PROXY, Connection:25, Time:38, Success:True\
Type:Prepared, **Batch:False**, QuerySize:1, BatchSize:0\
Query:["delete from tennis_player"]
        
`playerRepository.deleteAllInBatch();`
        
**SECOND APPROACH: deleteInBatch()**\
Output sample - **no batching**:\
Name:DATA_SOURCE_PROXY, Connection:25, Time:24, Success:True\
Type:Prepared, **Batch:False**, QuerySize:1, BatchSize:0\
Query:["delete from tennis_player where id=? or id=? or id=? or id=? or id=? ...]\
Params:[(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)]
        
`playerRepository.deleteInBatch(players);`
        
**THIRD APPROACH: use delete()**\
Output sample: - **batching**:\
Name:DATA_SOURCE_PROXY, Connection:25, Time:467, Success:True\
Type:Prepared, **Batch:True, QuerySize:1, BatchSize:7**\
Query:["delete from tennis_player where id=? and version=?"]\
Params:[(1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0)]\
...

`players.forEach(playerRepository::delete);`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
