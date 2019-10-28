**[How To Fetch DTO Via Blaze-Persistence Entity Views](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoBlazeEntityView)** 

**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on [Blaze-Persistence](https://persistence.blazebit.com/) entity views.
 
**Key points:**
- for Maven, add in `pom.xml` the dependencies specific to Blaze-Persistence
- configure Blaze-Persistence via `CriteriaBuilderFactory` and `EntityViewManager`
- write an *entity view* via an interface in Blaze-Persistence fashion
- write a Spring-centric repository by extending `EntityViewRepository`
- call method of this repository such as, `findAll()`, `findOne()`, etc
- for using Spring Data Projections check this [item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
