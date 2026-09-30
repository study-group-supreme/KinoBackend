package org.example.kinobackend.config;

import org.example.kinobackend.model.Employee;
import org.example.kinobackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class InitDataEmployee implements CommandLineRunner {


    private final EmployeeRepository employeeRepository;

    public InitDataEmployee(EmployeeRepository employeeRepository, EmployeeRepository employeeRepository1) {

        this.employeeRepository = employeeRepository1;
    }

    @Override
    public void run(String... args) {

       createEmployees();
    }
    private void createEmployees() {
        Employee employee = new Employee();


    }

}
