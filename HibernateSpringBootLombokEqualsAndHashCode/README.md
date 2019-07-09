**[Why To Avoid Lombok `@EqualsAndHashCode` in Entities And How To Override `equals()` And `hashCode()`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLombokEqualsAndHashCode)**

**Description:** Entities should implement `equals()` and `hashCode()` as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/). The main idea is that Hibernate requires that an entity is equal to itself across all its state transitions (*transient*, *attached*, *detached* and *removed*). Using Lombok `@EqualsAndHashCode` will not respect this requirment.

**Key points:**\
**AVOID THESE APPROACHES**\
     - Using Lombok default behavior of `@EqualsAndHashCode`\
     (entity: `LombokDefaultProduct`, test: `LombokDefaultEqualsAndHashCodeTest`)\
     - Using Lombok  `@EqualsAndHashCode` with primary key only\
     (entity: `LombokIdProduct`, test: `LombokEqualsAndHashCodeWithIdOnlyTest`)\
     - Rely on default `equals()` and `hashCode()`\
     (entity: `DefaultProduct`, test: `DefaultEqualsAndHashCodeTest`)\
     - Implement `equals()` and `hashCode()` based only on primary key\
     (entity: `IdProduct`, test: `IdEqualsAndHashCodeTest`)

**PREFER THESE APPROACHES**\
     - Rely on `@NaturalId` (entity: `NaturalIdProduct`, test: `NaturalIdEqualsAndHashCodeTest`)\
     - Rely on primary key (entity: `GoodProduct`, test: `GoodEqualsAndHashCodeTest`)
     
**Good implementation of equals() and hashCode() for database-generated identifiers:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLombokEqualsAndHashCode/auto-generated%20primary%20key%20and%20equals%20-%20hashCode.png)       

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
