**[DTOs via ResultTransformer](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoResultTransformer)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on Hibernate, `ResultTransformer`.

**Key points:**\
     - use `AliasToBeanConstructorResultTransformer` for DTOs without setters, with constructor\
     - use `Transformers.aliasToBean()` for DTOs with setters\
     - starting with Hibernate 5.2, `ResultTransformer` is deprecated, but until a replacement will be available (in Hibernate 6.0) it can be used ([read further](https://discourse.hibernate.org/t/hibernate-resulttransformer-is-deprecated-what-to-use-instead/232))\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections) 
