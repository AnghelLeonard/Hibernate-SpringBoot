**[How To Exploit Spring Projections(DTOs) And Join Unrelated Entities in Hibernate 5.1+](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoUnrelatedEntities)**

**Description:** This application is a proof of concept for using Spring Projections (DTOs) and join unrelated entities. Hibernate 5.1 introduced explicit joins on unrelated entities and the syntax and behaviour are similar to `SQL JOIN` statements.

**Key points:**\
     - define serveral entities (e.g., `Patient` and `Clinic` unrelated entities)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `PatientNameAndMedicalHistoryDto`)\
     - write joins queries using JPQL/SQL, for example:\
     - Query all patients names and medical history with no current treatment (`localhost:8080/allPatientsNameAndMedicalHistoryNoTreatmentInnerJoinJpql`)
