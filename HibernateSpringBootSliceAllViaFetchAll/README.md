**[How To Fetch `Slice<entity>`/`Slice<dto>` Via `fetchAll`/`fetchAllDto`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllViaFetchAll)**

**Story**: Spring Boot provides an *offset* based built-in paging mechanism that returns a `Page` or `Slice`. Each of these APIs represents a page of data and some metadata. The main difference is that `Page` contains the total number of records, while `Slice` can only tell if there is another page available. For `Page`, Spring Boot provides a `findAll()` method capable to take as arguments a `Pageable` and/or a `Specification`.  In order to populate a `Page` containing the total number of records, this method triggers an `SELECT COUNT` extra-query next to the query used to fetch the current page . This can be a performance penalty since the `SELECT COUNT` query is triggered every time we request a page. In order to avoid this extra-query, Spring Boot provides a more relaxed API, the `Slice` API. Using `Slice` instead of `Page` removes the need of this extra `SELECT COUNT` query and returns the page (records) and some metadata without the total number of records. So, while `Slice` doesn't know the total number of records, it still can tell if there is another page available after the current one or this is the last page. The problem is that `Slice` work fine for queries containing the SQL, `WHERE` clause (including those that uses the query builder mechanism built into Spring Data), but it **doesn't work** for `findAll()`. This method will still return a `Page` instead of `Slice` therefore the `SELECT COUNT` query is triggered for `Slice<T> findAll(...);`.

**Workaround:**
- The trick is to  simply define a method named `fetchAll()` that uses JPQL and `Pageable` to return `Slice<entity>`, and a method named `fetchAllDto()` that uses JPQL and `Pageable` as well to return `Slice<dto>`. So, avoid naming the method `findAll()`.

**Usage example:**\
`public Slice<Author> fetchNextSlice(int page, int size) {`\
&nbsp;&nbsp;&nbsp;&nbsp;`return authorRepository.fetchAll(PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "age")));`\
 `}`
 
 `public Slice<AuthorDto> fetchNextSliceDto(int page, int size) {`\
&nbsp;&nbsp;&nbsp;&nbsp;`return authorRepository.fetchAllDto(PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "age")));`\
 `}`
 
-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
