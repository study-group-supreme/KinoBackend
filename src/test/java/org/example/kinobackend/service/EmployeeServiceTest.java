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
        admin.setId(1);
        admin.setName("John");
        admin.setUsername("JohnAdmin");
        admin.setPassword("admin123");
        admin.setIsAdmin(true);

        employee = new Employee();
        employee.setId(2);
        employee.setName("Lone");
        employee.setUsername("LoneNotAdmin");
        employee.setPassword("notAdmin123");
        employee.setIsAdmin(false);
    }

    @Test
    public void createEmployeeCanCreateEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Andreas");
        employee.setPassword("Andreas123");
        employee.setUsername("AndreasUser");
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee result = employeeService.createEmployee(employee);
        assertEquals(employee, result);
        verify(employeeRepository).save(employee);
    }

    @Test
    public void createEmployeeCannotCreateEmployeeIfPasswordIsUnder3CharactersAndThrowsIllegalArgumentException() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Andreas");
        employee.setPassword("ab");
        employee.setUsername("Andreas123");
        assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    public void get_All_Employees() {

        when(employeeRepository.findAll()).thenReturn(List.of(admin, employee));
        List<Employee> result = employeeService.getAll();
        assertEquals(2, result.size());
        assertEquals("JohnAdmin", result.get(0).getUsername());
        assertEquals("LoneNotAdmin", result.get(1).getUsername());
        verify(employeeRepository).findAll();
    }

    @Test
    public void createEmployeeThrowsIllegalArgumentExceptionIfNameIsBlank() {
        Employee employee = new Employee();
        employee.setPassword("12345");
        employee.setName("   ");
        employee.setId(2);
        employee.setUsername("BlankUser");
        assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    public void createEmployeeThrowsIllegalArgumentExceptionIfNameIsNull() {
        Employee employee = new Employee();
        employee.setName(null);
        employee.setId(1);
        employee.setPassword("hans123");
        employee.setUsername("Hans123321");

        assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    public void createEmployeeThrowsIllegalArgumentExceptionIfUsernameIsNull() {
        Employee employee = new Employee();
        employee.setUsername(null);
        employee.setName("Joakim");
        employee.setPassword("sadfgh");
        employee.setId(1);
        assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    public void createEmployeeThrowsIllegalArgumentExceptionIfUsernameIsBlank() {
        Employee employee = new Employee();
        employee.setUsername("     ");
        employee.setName("Joakim");
        employee.setPassword("sadfgh");
        employee.setId(1);
        Optional<Employee> result = employeeService.findByUsername("JohnAdmin");

        assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(employee));
    }

    @Test
    public void createEmployeeThrowsIllegalArgumentExceptionIfUsernameIsAlreadyTaken() {
        Employee employee1 = new Employee();
        employee1.setUsername("test");
        employee1.setName("Mads");
        employee1.setId(1);
        employee1.setPassword("1234");
        when(employeeRepository.existsByUsername("test")).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> employeeService.createEmployee(employee1));
    }

    @Test
    public void deleteEmployeeShouldDeleteEmployee() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1);

        verify(employeeRepository).findById(1);
        verify(employeeRepository).delete(employee);
    }

    @Test
    public void deleteEmployeeThrowsEntityNotFoundExceptionIfEmployeeToBeDeletedIsNotFound() {
        when(employeeRepository.findById(5)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> employeeService.deleteEmployee(5));

        verify(employeeRepository, never()).delete(any());
    }

    @Test
    public void getEmployeeByIdShouldReturnEmployeeWithTheRightId() {
        when(employeeRepository.findById(2)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(2);

        assertEquals(2, result.getId());
    }

    @Test
    public void getEmployeeByIdShouldThrowEntityNotFoundExceptionWhenNoEmployeeIsFound() {
        when(employeeRepository.findById(1000000)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> employeeService.getEmployeeById(1000000));
    }

    @Test
    public void updateEmployee() {

        admin.setId(1);

        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("John Updated");
        updatedEmployee.setUsername("JohnUpdated");
        updatedEmployee.setPassword("newPassword123");
        updatedEmployee.setIsAdmin(true);

        when(employeeRepository.existsByUsername("JohnUpdated"))
                .thenReturn(false);

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(admin));

        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(admin);

        Optional<Employee> result =
                employeeService.updateEmployee(1, updatedEmployee);

        assertTrue(result.isPresent());
        assertEquals("JohnUpdated", result.get().getUsername());
        assertEquals("John Updated", result.get().getName());
        assertEquals("newPassword123", result.get().getPassword());
        assertTrue(result.get().isAdmin());

        verify(employeeRepository).existsByUsername("JohnUpdated");
        verify(employeeRepository).findById(1);
        verify(employeeRepository).save(admin);
    }

    @Test
    public void updateEmployeeUsernameAlreadyTaken() {

        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("John Updated");
        updatedEmployee.setUsername("ExistingUser");
        updatedEmployee.setPassword("newPassword123");
        updatedEmployee.setIsAdmin(true);

        when(employeeRepository.existsByUsername("ExistingUser"))
                .thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> employeeService.updateEmployee(1, updatedEmployee)
        );

        assertEquals("Username already taken", exception.getMessage());

        verify(employeeRepository).existsByUsername("ExistingUser");
        verify(employeeRepository, never()).findById(anyInt());
        verify(employeeRepository, never()).save(any(Employee.class));
    }


}
