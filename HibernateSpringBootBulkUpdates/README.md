**[How To *Bulk* Updates](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBulkUpdates)**

**Description:** *Bulk* operations (updates and deletes) are fastest than batching, can benefit from indexing, but they have two main drawbacks:

- after *bulk* update/delete, the Persistent Context may contain outdated entities (it is advisable to flush (before update/delete) and close/clear (after update/delete) the Persistent Context accordingly to avoid issues created by unflushed (if any) or outdated (if any) entities)
- *bulk* update/delete don't benefit of application-level optimistic locking mechanisms, therefore the *lost updates* not prevented (it is advisable to signal these updates by explicitly incrementing `version` (if any)).

This application provides examples of *bulk* updates for `Author` and `Book` entities (between `Author` and `Book` there is a bidirectional lazy `@OneToMany` relationship). Both, `Author` and `Book`, has a `version` field.

**Key points:**\
     - this application provide an example of *bulk* updates that don't involve entities (data is not loaded in the Persistent Context)\
     - this application provide an example of *bulk* updates that involve entities (data is loaded in the Persistent Context before update it via *bulk* operations)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
