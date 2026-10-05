package org.example.kinobackend.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.kinobackend.model.Employee;
import org.example.kinobackend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


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
        if (employee.getUsername() == null || employee.getUsername().isBlank()) {
            throw new IllegalArgumentException("Employee must have a username");
        }
        if (employeeRepository.existsByUsername(employee.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }
        return employeeRepository.save(employee);
    }

    public Optional<Employee> updateEmployee(int id, Employee employee) {
        if (employee.getName() == null || employee.getName().isBlank()) {
            throw new IllegalArgumentException("Employee must have a name");
        }

        if (employee.getPassword() == null || employee.getPassword().isBlank()) {
            throw new IllegalArgumentException("Employee password cannot be empty");
        }
        if (employee.getPassword().length() <= 3) {
            throw new IllegalArgumentException("Password must be longer than 3 characters");
        }
        if (employee.getUsername() == null || employee.getUsername().isBlank()) {
            throw new IllegalArgumentException("Employee must have a username");
        }
        if (employeeRepository.existsByUsername(employee.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow();
        existingEmployee.setUsername(employee.getUsername());
        existingEmployee.setPassword(employee.getPassword());
        existingEmployee.setName(employee.getName());
        existingEmployee.setIsAdmin(employee.isAdmin());

        return Optional.of(employeeRepository.save(existingEmployee));
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    public void deleteEmployee(int id) {
        Employee employeeToBeDeleted = employeeRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException
                ("The employee you are trying to deleted does not exist"));
        employeeRepository.delete(employeeToBeDeleted);
    }

    public Employee getEmployeeById(int id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("No employee found"));
        return employee;
    }

}
