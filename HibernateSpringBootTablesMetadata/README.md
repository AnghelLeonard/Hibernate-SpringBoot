**[How To Extract Tables Metadata](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTablesMetadata)**
 
**Description:** This application is an example of using the Hibernare SPI, `org.hibernate.integrator.spi.Integrator` for extracting tables metadata.

**Key points:**\
     - Implement `org.hibernate.integrator.spi.Integrator` and override `integrate()` method to return `metadata.getDatabase()`\
     - Register this `Integrator` via `LocalContainerEntityManagerFactoryBean`
     
-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
