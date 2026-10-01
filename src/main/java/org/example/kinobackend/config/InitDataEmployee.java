package org.example.kinobackend.config;

import org.example.kinobackend.model.Employee;
import org.example.kinobackend.repository.EmployeeRepository;
import org.example.kinobackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;




@Component
public class InitDataEmployee implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public InitDataEmployee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Employee admin1 = new Employee();
        admin1.setName("John");
        admin1.setPassword("admin123");
        admin1.setIsAdmin(true);
        admin1.setUsername("JohnAdmin");

        Employee notAdmin = new Employee();
        notAdmin.setName("Lone");
        notAdmin.setPassword("notAdmin123");
        notAdmin.setIsAdmin(false);
        notAdmin.setUsername("LoneNotAdmin");

        employeeRepository.save(admin1);
        employeeRepository.save(notAdmin);
    }
}


