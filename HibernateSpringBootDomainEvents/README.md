**[How To Publish Domain Events From Aggregate Root](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDomainEvents)**
 
**Note:** Domain events should be used with extra-caution! The best practices for using them are revealed in my book, [Spring Boot Persistence Best Practices](https://www.apress.com/us/book/9781484256251).
 
**Description:** Starting with Spring Data Ingalls release publishing domain events by aggregate roots becomes easier. Entities managed by repositories are aggregate roots. In a Domain-Driven Design application, these aggregate roots usually publish domain events. Spring Data provides an annotation `@DomainEvents` you can use on a method of your aggregate root to make that publication as easy as possible. A method annotated with `@DomainEvents` is automatically invoked by Spring Data whenever an entity is saved using the right repository. Moreover, Spring Data provides the  `@AfterDomainEventsPublication` annotation to indicate the method that should be automatically called for clearing events after publication. Spring Data Commons comes with a convenient template base class (`AbstractAggregateRoot`) to help to register domain events and is using the publication mechanism implied by `@DomainEvents` and `@AfterDomainEventsPublication`. The events are registered by calling the `AbstractAggregateRoot.registerEvent()` method. The registered domain events are published if we call one of the *save* methods (e.g., `save()`) of the Spring Data repository and cleared after publication.

This is a sample application that relies on `AbstractAggregateRoot` and its `registerEvent()` method. We have two entities, `Book` and `BookReview` involved in a lazy-bidirectional `@OneToMany` association. A new book review is saved in `CHECK` status and a `CheckReviewEvent` is published. This event handler is responsible to check the review grammar, content, etc and switch the review status from `CHECK` to `ACCEPT` or `REJECT` and propagate the new status to the database. So, this event is registered before saving the book review in `CHECK` status and is published automatically after we call the `BookReviewRepository.save()` method. After publication, the event is cleared.

**Key points:**
- the entity (aggregate root) that publish events should extend `AbstractAggregateRoot` and provide a method for registering events
- here, we register a single event (`CheckReviewEvent`), but more can be registered 
- event handling take place is `CheckReviewEventHandler` in an asynchronous manner via `@Async`
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

