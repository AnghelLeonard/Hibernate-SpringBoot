**[How To Ensure/Validate That Only One Association Is Non-null](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootChooseOnlyOneAssociation)**
 
 **Description:** Consider an entity named `Review`. This entity defines three `@ManyToOne` relationships to `Book`, `Article` and `Magazine`. A review can be associated with either a book, a magazine or an article. To validate this constraint, we can rely on  [Bean Validation](https://beanvalidation.org/) as in this application.

**Key points:**\
     - rely on Bean Validation to validate that only one association is non-`null`\
     - expose the constraint via a custom annotation (`@JustOneOfMany`) added at class-level to the `Review` entity
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
