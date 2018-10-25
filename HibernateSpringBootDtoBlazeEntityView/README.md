**[DTOs via Blaze-Persistence Entity Views](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoBlazeEntityView)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on [Blaze-Persistence](https://persistence.blazebit.com/) entity views.

**Key points:**\
     - add in pom.xml dependencies specific to Blaze-Persistence\
     - configure Blaze-Persistence, `CriteriaBuilderFactory` and `EntityViewManager`\
     - write an entity view via an interface in Blaze-Persistence fashion\
     - write a Spring-centric repository by extending `EntityViewRepository`\
     - call method of this repository such as, `findAll()`, `findOne()`, etc\
     - starting with Hibernate 5.2, `ResultTransformer` is deprecated, but until a replacement will be available (in Hibernate 6.0) it can be used ([read further](https://discourse.hibernate.org/t/hibernate-resulttransformer-is-deprecated-what-to-use-instead/232))\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)
