package org.example.kinobackend.controller;


import org.example.kinobackend.model.Employee;
import org.example.kinobackend.repository.EmployeeRepository;
import org.example.kinobackend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @GetMapping("")
    public List<Employee> showEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<Employee> getEmployeeByUsername(@PathVariable String username) {

        return employeeService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeService.createEmployee(employee);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable int id){
        Employee employeeToBeRemoved = employeeService.getEmployeeById(id);
        employeeService.deleteEmployee(employeeToBeRemoved.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
