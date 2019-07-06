**[How To Implement Offset Pagination in Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOffsetPagination)**

**Description:** This is a classical Spring Boot *offset* pagination example. However, is not advisable to use this approach in production because of its performance penalties explained further. 

When we rely on an *offset* pagination, we have the performance penalty induced by throwing away *n* records before reaching the desired *offset*. Larger *n* leads to a significant performance penalty. Another penalty is the extra-`SELECT` needed to count the total number of records. In order to understand how bad *offset* pagination can perform please check [this](http://allyouneedisbackend.com/blog/2017/09/24/the-sql-i-love-part-1-scanning-large-table/) article. A screenshot from that article is below:
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootKeysetPagination/offset%20pagination.png)
Nevertheless, maybe this example is a little bit extreme. For relatively small datasets, *offset* pagination is not so bad (it is close in performance to *keyset* pagination), and, since Spring Boot provides built-in support for *offset* pagination via the `Page` API, it is very easy to use it. However, depending on the case, we can optimize a little bit the *offset* pagination as in the following examples:

- [Trigger `COUNT(*) OVER` And Return `Page<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPaginationWF)
- [Trigger `COUNT(*) OVER` And Return `Page<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationExtraColumnWF)
- [Trigger `SELECT COUNT` Subquery And Return `Page<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPagination)
- [Trigger `SELECT COUNT` Subquery And Return `Page<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationExtraColumn)
- [Trigger `SELECT COUNT` Subquery And Return `Page<projection>` That Maps Entities And The Total Number Of Records Via Projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationProjection)

- [Trigger `SELECT COUNT` Subquery And Return `List<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListDtoOffsetPagination)
- [Trigger `COUNT(*) OVER` And Return `List<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListDtoOffsetPaginationWF)
- [Trigger `SELECT COUNT` Subquery And Return `List<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationExtraColumn)
- [Trigger `COUNT(*) OVER` And Return `List<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationExtraColumnWF)
- [Trigger `SELECT COUNT` Subquery And Return `List<projection>` That Maps Entities And The Total Number Of Records Via Projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationProjection)

**But:** If *offset* pagination is causing you performance issues and you decide to go with *keyset* pagination then please check [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootKeysetPagination) (*keyset* pagination).

**Key points of classical *offset* pagination:**\
     - write a repository that extends `PagingAndSortingRepository`\
     - call or write methods that returns `Page<entity>`

**Examples of classical *offset* pagination:**\
     - call the built-in `findAll(Pageable)` without sorting:\
     `repository.findAll(PageRequest.of(page, size));`\
     - call the built-in `findAll(Pageable)` with sorting:\
     `repository.findAll(PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name")));`\
     - use Spring Data query creation to define new methods in your repository:\
     `Page<Author> findByName(String name, Pageable pageable);`\
     `Page<Author> queryFirst10ByName(String name, Pageable pageable);`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
