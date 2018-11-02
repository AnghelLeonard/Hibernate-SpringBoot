package com.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClinicController {

    @Autowired
    private ClinicService clinicService;
   
    // Query all patients names and medical history with no current treatment (JPQL)
    @RequestMapping("/allPatientsNameAndMedicalHistoryNoTreatmentInnerJoinJpql")
    public List<PatientNameAndMedicalHistoryDto> allPatientsNameAndMedicalHistoryNoTreatmentInnerJoinJpql() {

       return clinicService.fetchAllPatientsNameAndMedicalHistoryNoTreatmentInnerJoinJpql();
    }        
}
