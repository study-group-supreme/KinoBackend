package org.example.kinobackend.service;

import org.example.kinobackend.model.Employee;
import org.example.kinobackend.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;
    private Employee admin;
    private Employee employee;

    @BeforeEach
    public void setUp() {

        admin = new Employee();
        admin.setName("John");
        admin.setUsername("JohnAdmin");
        admin.setPassword("admin123");
        admin.setIsAdmin(true);

        employee = new Employee();
        employee.setName("Lone");
        employee.setUsername("LoneNotAdmin");
        employee.setPassword("notAdmin123");
        employee.setIsAdmin(false);
    }

    @Test
    public void get_All_Employees() {

        when(employeeRepository.findAll()).thenReturn(List.of(admin, employee));
        List<Employee> result = employeeService.getAll();
//        assertEquals(2, result.size());
//        assertEquals("JohnAdmin", result.get(0).getUsername());
//        assertEquals("LoneNotAdmin", result.get(1).getUsername());
        verify(employeeRepository).findAll();
    }

    @Test
    public void get_Employee_By_Username() {

        when(employeeRepository.findByUsername("JohnAdmin"))
                .thenReturn(Optional.of(admin));

        Optional<Employee> result = employeeService.findByUsername("JohnAdmin");

        assertTrue(result.isPresent());
        assertEquals("JohnAdmin", result.get().getUsername());
        assertEquals("John", result.get().getName());

        verify(employeeRepository).findByUsername("JohnAdmin");
    }
}