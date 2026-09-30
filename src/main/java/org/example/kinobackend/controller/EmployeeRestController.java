package org.example.kinobackend.controller;


import org.example.kinobackend.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeRestController {
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeRepository.getAll();
    }



}
