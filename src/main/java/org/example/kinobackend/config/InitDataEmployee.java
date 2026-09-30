package org.example.kinobackend.config;

import org.example.kinobackend.model.Employee;
import org.example.kinobackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class InitDataEmployee implements CommandLineRunner {

    @Autowired
    EmployeeRepository employeeRepository;


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

        employeeRepository.save(admin1);
        employeeRepository.save(notAdmin);
    }
}


