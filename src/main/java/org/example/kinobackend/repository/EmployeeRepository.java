package org.example.kinobackend.repository;

import org.example.kinobackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existByUsername(String username);
}
