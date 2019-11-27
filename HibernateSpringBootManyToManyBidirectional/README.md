
**[The Best Way To Implement A Bidirectional `@ManyToMany` Association](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManyBidirectional)**

**Description:** This application is a proof of concept of how it is correct to implement the bidirectional `@ManyToMany` association from the performance perspective. 

**Key points:**
- choose an owning and a `mappedBy` side
- materialize the relationships collections via `Set` not `List`
- use helper methods on the owner of the relationship to keep both sides of the association in sync
- on the owner of the relationship use `CascadeType.PERSIST` and `CascadeType.MERGE`, but avoid `CascadeType.REMOVE/ALL`
- on the owner of the relationship set up join table
- `@ManyToMany` is lazy by default; keep it this way!
- as entities identifiers, use assigned identifiers (business key, natural key (`@NaturalId`)) and/or database-generated identifiers and override (on both sides) properly the `equals()` and `hashCode()` methods as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/)
- if `toString()` need to be overridden, then pay attention to involve only for the basic attributes fetched when the entity is loaded from the database

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

