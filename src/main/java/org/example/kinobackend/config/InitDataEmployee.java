package org.example.kinobackend.config;

import org.example.kinobackend.model.Employee;
import org.example.kinobackend.repository.EmployeeRepository;
import org.example.kinobackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;




@Component
public class InitDataEmployee implements CommandLineRunner {

    private final EmployeeService employeeService;


    public InitDataEmployee(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @Override
    public void run(String... args) throws Exception {

        Employee admin1 = new Employee();
        admin1.setName("John");
        admin1.setPassword("admin123");
        admin1.setIs_admin(true);
        admin1.setUsername("JohnAdmin");

        Employee notAdmin = new Employee();
        notAdmin.setName("Lone");
        notAdmin.setPassword("notAdmin123");
        notAdmin.setIs_admin(false);
        notAdmin.setUsername("LoneNotAdmin");

        employeeService.save(admin1);
        employeeService.save(notAdmin);
    }
}


