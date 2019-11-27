**[How `@ElementCollection` With `@OrderColumn` Works](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootElementCollectionWithOrderColumn)**

**Description:** This application reveals the performance penalties of using `@ElementCollection`. In this case, with `@OrderColumn`. But, as you can see in this application (in comparison with item 33), by adding `@OrderColumn` can mitigate some performance penalties when operations takes place near the collection tail (e.g., add/remove at/from the end of the collection). Mainly, all elements situated before the adding/removing entry are left untouched, so the performance penalty can be ignored if we affect rows close to the collection tail.

**Key points:**
- an `@ElementCollection` doesn't have a primary key
- an `@ElementCollection` is mapped in a separate table
- prefer `@ElementCollection` with `@OrderColumn` when you have a lot of inserts and deletes near the collection tail
- the more elements are inserted/removed from the beginning of the collection the greater the performance penalty will be
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootElementCollectionWithOrderColumn/%40ElementCollection%20with%20%40OrderColumn.png)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

