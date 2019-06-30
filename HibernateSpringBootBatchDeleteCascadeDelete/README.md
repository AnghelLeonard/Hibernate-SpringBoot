**[How To Batch Deletes In MySQL Via SQL "ON DELETE CASCADE"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteCascadeDelete)**

**Description:** Batch deletes in MySQL via `ON DELETE CASCADE`. Auto-generated database schema will contain `ON DELETE CASCADE` directive.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use *classical* delete batching. The first one simply triggers a `delete from entity_name` statement, while the second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement. Both of them take advantage on `ON DELETE CASCADE`. For *classical* delete batching rely on `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or `delete()` method. Behind the scene, the two flavors of `deleteAll()` relies on `delete()`.

**Key points:**\
     - in this application, we have a `Author` entity and each author can have several `Book` (*one-to-many*)\
     - first, we remove `orphanRemoval` or set it to `false`\
     - second, we use only `CascadeType.PERSIST` and `CascadeType.MERGE`\
     - third, we set `@OnDelete(action = OnDeleteAction.CASCADE)` next to `@OneToMany`\
     - fourth, we set `spring.jpa.properties.hibernate.dialect` to `org.hibernate.dialect.MySQL5InnoDBDialect`\   
        
**Output example:**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchDeleteCascadeDelete/batch%20delete%20via%20SQL%20cascade%20delete.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
