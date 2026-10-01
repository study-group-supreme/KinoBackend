package org.example.kinobackend.service;

import org.example.kinobackend.model.Employee;
import org.example.kinobackend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

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
        if (employee.getPassword().length() >= 3) {
            throw new IllegalArgumentException("Password must be longere than 3 characters");
        }
        return employeeRepository.save(employee);
    }
}
