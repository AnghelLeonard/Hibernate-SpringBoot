package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Inner Join
    // Query all patients names and medical history with no current treatment (JPQL)
    @Query(value = "SELECT a.pname as pname, b.mhistory as mhistory "
            + "FROM Patient a INNER JOIN Clinic b ON a.pname = b.pname "
            + "WHERE b.ptreatment = 'none'")
    List<PatientNameAndMedicalHistoryDto> fetchAllPatientsNameAndMedicalHistoryNoTreatmentInnerJoinJpql();
    
}
