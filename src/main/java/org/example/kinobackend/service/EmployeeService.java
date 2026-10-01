package org.example.kinobackend.service;

import org.example.kinobackend.model.Employee;
import org.example.kinobackend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getName() == null || employee.getName().isBlank()) {
            throw new IllegalArgumentException("Employee must have a name");
        }
        if (employee.getPassword() == null || employee.getPassword().isBlank()) {
            throw new IllegalArgumentException("Employee password cannot be empty");
        }
        if (employee.getPassword().length() <= 3) {
            throw new IllegalArgumentException("Password must be longere than 3 characters");
        }
        if (employee.getUsername() == null || employee.getUsername().isBlank()){
            throw new IllegalArgumentException("Employee must have a username");
        }
        if(employeeRepository.existsByUsername(employee.getUsername())){
            throw new IllegalArgumentException("Username already taken");
        }
        return employeeRepository.save(employee);
    }
}
