**[Why To Avoid Lombok `@EqualsAndHashCode` And `@Data` In Entities And How To Override `equals()` And `hashCode()`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLombokEqualsAndHashCode)**
 
**Description:** Entities should implement `equals()` and `hashCode()` as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/). The main idea is that Hibernate requires that an entity is equal to itself across all its state transitions (*transient*, *attached*, *detached* and *removed*). Using Lombok `@EqualsAndHashCode` (or `@Data`) will not respect this requirment.

**Key points:**\
**AVOID THESE APPROACHES**
- Using Lombok default behavior of `@EqualsAndHashCode`
(entity: `LombokDefaultBook`, test: `LombokDefaultEqualsAndHashCodeTest`)
- Using Lombok  `@EqualsAndHashCode` with primary key only
(entity: `LombokIdBook`, test: `LombokEqualsAndHashCodeWithIdOnlyTest`)
- Rely on default `equals()` and `hashCode()`
(entity: `DefaultBook`, test: `DefaultEqualsAndHashCodeTest`)
- Rely on default `equals()` and `hashCode()` containing only the database-generated identifier
(entity: `IdBook`, test: `IdEqualsAndHashCodeTest`)

**PREFER THESE APPROACHES**
- Rely on business key (entity: `BusinessKeyBook`, test: `BusinessKeyEqualsAndHashCodeTest`)
- Rely on `@NaturalId` (entity: `NaturalIdBook`, test: `NaturalIdEqualsAndHashCodeTest`)
- Rely on manually assigned identifiers (entity: `IdManBook`, test: `IdManEqualsAndHashCodeTest`)
- Rely on database-generated identifiers (entity: `IdGenBook`, test: `IdGenEqualsAndHashCodeTest`)
     
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLombokEqualsAndHashCode/auto-generated%20primary%20key%20and%20equals%20-%20hashCode.png)       

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
