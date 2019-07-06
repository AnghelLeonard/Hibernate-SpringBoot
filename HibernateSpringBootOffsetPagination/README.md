**[How To Implement Offset Pagination in Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOffsetPagination)**

**Description:** This is a classical Spring Boot *offset* pagination example. However, is not advisable to use this approach in production because of its performance penalties explained further. 

When we rely on an *offset* pagination, we have the performance penalty induced by throwing away *n* records before reaching the desired *offset*. Larger *n* leads to a significant performance penalty. Another penalty is the extra-`SELECT` needed to count the total number of records. In order to understand how bad offset can perform please check [this](http://allyouneedisbackend.com/blog/2017/09/24/the-sql-i-love-part-1-scanning-large-table/) article. A screenshot from that article is below:
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootKeysetPagination/offset%20pagination.png)
Alternatively, we can rely on an optimized *offset* pagination or on *keyset* pagination. Nevertheless, for relatively small datasets, *offset* and *keysey* provides almost the same performances, and, since Spring Boot provides built-in support for *offset* pagination via the `Page` API, it is very easy to use it. Therefore, you will probably prefer an optimized *offset* pagination as in the following examples:


**But:** If *offset* pagination is causing you performance issues and you decide to go with *keyset* pagination then please check the following items: [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderSortAndSpecificationAndQueryHints) (slice technique for find all records), [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootWindowFunctionPaging) (*offset* with window functions) and [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootKeysetPagination) (*keyset* pagination).

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
