**[Why You Should Avoid Unidirectional `@OneToMany` And Prefer Bidirectional `@OneToMany` Relationship](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToManyUnidirectional)**

**Description:** As a rule of thumb, unidirectional `@OneToMany` association is less efficient than the bidirectional `@OneToMany` or the unidirectional `@ManyToOne` associations. This application is a sample that exposes the DML statements generated for reads, writes and removal operations when the unidirectional `@OneToMany` mapping is used.

**Key points:**
- regular unidirectional `@OneToMany` is less efficient than bidirectional `@OneToMany` association
- using `@OrderColumn` come with some optimizations for removal operations but is still less efficient than bidirectional `@OneToMany` association
- using `@JoinColumn` eliminates the junction table but is still less efficient than bidirectional `@OneToMany` association
- using `Set` instead of `List` or bidirectional `@OneToMany` with `@JoinColumn` relationship (e.g., `@ManyToOne @JoinColumn(name = "author_id", updatable = false, insertable = false)`) still performs worse than bidirectional `@OneToMany` association
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

