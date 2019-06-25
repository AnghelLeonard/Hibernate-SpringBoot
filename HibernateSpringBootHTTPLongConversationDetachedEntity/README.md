**[HTTP Long Conversation Via Versioned Optimistic Locking And Detached Entities In Session](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHTTPLongConversationDetachedEntity)**

**Description:** This is a sample application that shows how to take advantage of versioned optimistic locking and detached entities in HTTP long conversations. The climax consists of storing the detached entities across multiple HTTP requests. Commonly, this can be accomplished via HTTP session. 

**Key points:**\
     - prepare the entity via `@Version`\
     - rely on `@SessionAttributes` for storing the detached entities
     
**Sample output of optimistic locking exception:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHTTPLongConversationDetachedEntity/http%20long%20conversations%20detached%20entity%20ole.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
