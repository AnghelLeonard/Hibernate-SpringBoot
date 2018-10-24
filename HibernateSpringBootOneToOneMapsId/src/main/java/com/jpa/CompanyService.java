package com.jpa;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyService {

    private final EmployeeRepository employeeRepository;
    private final DeskRepository deskRepository;

    public CompanyService(EmployeeRepository employeeRepository, DeskRepository deskRepository) {
        this.employeeRepository = employeeRepository;
        this.deskRepository = deskRepository;
    }

    public void newEmployee() {

        Employee employee = new Employee();
        employee.setName("Marius T");

        employeeRepository.save(employee);
    }

    @Transactional
    public void newDeskForEmployee() {

        Employee employee = employeeRepository.findByName("Marius T");

        Desk desk = new Desk();
        desk.setPosition("left-corner");
        desk.setEmployee(employee); // this will set the ID of desk as the ID of employee

        deskRepository.save(desk);
    }

    @Transactional(readOnly=true)
    public Desk findDeskByEmployeeId() {        
        
        Employee employee = employeeRepository.findByName("Marius T");        
        Optional<Desk> desk = deskRepository.findById(employee.getId());       

        return desk.get();
    }
}
