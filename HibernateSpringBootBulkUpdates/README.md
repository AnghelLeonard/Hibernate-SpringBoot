**[How To *Bulk* Updates](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBulkUpdates)**

**Description:** *Bulk* operations (updates and deletes) are faster than batching, can benefit from indexing, but they have two main drawbacks:

- *bulk* updates/deletes may leave the Persistence Context in an outdated state (it's up to you to prevent this issue by flushing the Persistence Context before update/delete and close/clear it after the update/delete to avoid issues created by potentially unflushed or outdated entities)
- *bulk* updates/deletes don't benefit of automatic optimistic locking mechanisms (e.g., `@Version` is ignored), therefore the *lost updates* are not prevented (it is advisable to signal these updates by explicitly incrementing `version` (if any is present)).

This application provides examples of *bulk* updates for `Author` and `Book` entities (between `Author` and `Book` there is a bidirectional lazy `@OneToMany` association). Both, `Author` and `Book`, has a `version` field.

**Key points:**
- this application provide an example of *bulk* updates that don't involve managed entities (data is not loaded in the Persistence Context)
- this application provide an example of *bulk* updates that involve managed entities (data is loaded in the Persistence Context before update it via *bulk* operations)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

