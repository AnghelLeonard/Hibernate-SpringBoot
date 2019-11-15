**[How To Publish Domain Events From Aggregate Root](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDomainEvents)**
 
**Description:** Starting with Spring Data Ingalls release publishing domain events by aggregate roots becomes easier. Entities managed by repositories are aggregate roots. In a Domain-Driven Design application, these aggregate roots usually publish domain events. Spring Data provides an annotation `@DomainEvents` you can use on a method of your aggregate root to make that publication as easy as possible. Spring Data Commons comes with a convenient base class (`AbstractAggregateRoot`) to help to register domain events and is using the publication mechanism implied by `@DomainEvents` and `@AfterDomainEventsPublication` (the method annotated with this annotation is commonly used for clear up events and is called by Spring after all events have been published). The registered domain events are published if we call one of the `save()` methods of the Spring Data repository and cleared after publication.

This is a sample application that relies on `AbstractAggregateRoot` and its `registerEvent()` method. We have two entities, `Book` and `BookReview` involved in a lazy-bidirectional association. A new book review is saved in `CHECK` status and a `CheckReviewEvent` is published. This event is responsible to check the review grammar, content, etc and switch the review status from `CHECK` to `ACCEPT` or `REJECT` and send a corresponding e-mail to the reviewer. So, this event is registered before saving the book review in `CHECK` status and is published automatically after we call the `BookReviewRepository.save()` method. After publication, the event is cleared.

**Key points:**
- the entity that publish events should extend `AbstractAggregateRoot` and provide a method for registering events
- here, we register a single event (`CheckReviewEvent`), but more can be registered 
- event processing take place in `CheckReviewEventProcessor` in an asynchronous manner via `@Async`
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
