package org.example.kinobackend.service;

import org.example.kinobackend.model.Employee;
import org.example.kinobackend.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks EmployeeService employeeService;

    @BeforeEach
    void setup(){
    }
    @Test
    public void createEmployeeCanCreateEmployee(){
        Employee employee = new Employee();
        employee.setId(1);
        employee.setIs_admin(true);
        employee.setName("Andreas");
        employee.setPassword("Andreas123");

        when(employeeRepository.save(employee)).thenReturn(employee);
Employee result = employeeService.createEmployee(employee);
        assertEquals(employee, result);
        verify(employeeRepository).save(employee);
    }
    @Test
    public void createEmployeeCannotCreateEmployeeIfPasswordIsUnder3CharactersAndThrowsIllegalArgumentException() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setIs_admin(true);
        employee.setName("Andreas");
        employee.setPassword("ab");
assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(employee));
    }
    @Test
    public void createEmployeeThrowsIllegalArgumentExceptionIfNameIsBlank(){
        Employee employee = new Employee();
        employee.setPassword("12345");
        employee.setName("   ");
        employee.setIs_admin(false);
        employee.setId(2);
        assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(employee));
    }
}
