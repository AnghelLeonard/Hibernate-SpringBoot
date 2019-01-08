**[How To Remove The Extra SELECT COUNT Query in Spring Boot Slice Paging with findAll()](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderSortAndSpecificationAndQueryHints)**

**This implementation:**
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderSortAndSpecificationAndQueryHints) is an implementation that allows us to provide a `Sort`, a `LockModeType`, a `QueryHints` and a Spring Data `Specification`

**Usage example:**\
`public static Specification<Player> isAgeGt21() {`\
&nbsp;&nbsp;&nbsp;&nbsp;`...`\
`}`

`public Slice<Player> fetchNextSlice(int page, int size) {`\
&nbsp;&nbsp;&nbsp;&nbsp;`// hint example`\
&nbsp;&nbsp;&nbsp;&nbsp;`Map<String, Object> hints = new HashMap<>();`\
&nbsp;&nbsp;&nbsp;&nbsp;`hints.put("...", value);`
        
&nbsp;&nbsp;&nbsp;&nbsp;`return playerRepository.findAll(isAgeGt21(),`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name")),`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`LockModeType.OPTIMISTIC_FORCE_INCREMENT);`\
`}`

**Other implementations:**:
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllSimpleSql) is a thin implementation based on a hard-coded SQL
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilder) is just another minimalist implementation based on `CriteriaBuilder` instead of hard-coded SQL
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderAndSort) is an implementation that allows us to provide a `Sort`, so sorting records is possible
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderSortAndSpecification) is an implementation that allows us to provide a `Sort` and a Spring Data `Specification`
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderSimpleJpaRepository) is an implementation that allows us to provide a `Sort`, a `LockModeType`, a `QueryHints` and a Spring Data `Specification` by extending the `SimpleJpaRepository` from Spring Data. Bascially, this implementation is the only one that returns `Page<T>` instead of `Slice<T>`, but it doesn't trigger the extra `SELECT COUNT` since it was eliminated by overriding this method. The main drawback is that by returing a `Page<T>` you don't know if there is a next page or the current one is the last. Nevertheless, there are workarounds to have this as well.

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
