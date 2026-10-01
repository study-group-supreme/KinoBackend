package org.example.kinobackend.repository;

import org.example.kinobackend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean findByUsername(String username);

}

