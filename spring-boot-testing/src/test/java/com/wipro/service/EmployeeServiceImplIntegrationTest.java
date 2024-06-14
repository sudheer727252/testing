package com.wipro.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wipro.dao.EmployeeRepository;
import com.wipro.entity.Employee;


@ExtendWith(SpringExtension.class)
public class EmployeeServiceImplIntegrationTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        Employee john = new Employee("john","depp");
        john.setId(1);

        Employee bob = new Employee("bob","williams");
        Employee alex = new Employee("ravi","kumar");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByFirstName(john.getFirstName())).thenReturn(john);
        Mockito.when(employeeRepository.findByFirstName(alex.getFirstName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByFirstName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99)).thenReturn(Optional.empty());
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "alex";
        Employee found = employeeService.getEmployeeByFirstName(name);

        assertThat(found.getFirstName()).isEqualTo(name);
    }

    @Test
    public void whenInValidName_thenEmployeeShouldNotBeFound() {
        Employee fromDb = employeeService.getEmployeeByFirstName("wrong_name");
        assertThat(fromDb).isNull();

        verifyFindByNameIsCalledOnce("wrong_name");
    }

//    @Test
//    public void whenValidName_thenEmployeeShouldExist() {
//        boolean doesEmployeeExist = employeeService.exists("john");
//        assertThat(doesEmployeeExist).isEqualTo(true);
//
//        verifyFindByNameIsCalledOnce("john");
//    }
//
//    @Test
//    public void whenNonExistingName_thenEmployeeShouldNotExist() {
//        boolean doesEmployeeExist = employeeService.exists("some_name");
//        assertThat(doesEmployeeExist).isEqualTo(false);
//
//        verifyFindByNameIsCalledOnce("some_name");
//    }
//
//    @Test
//    public void whenValidId_thenEmployeeShouldBeFound() {
//        Employee fromDb = employeeService.getEmployeeById(11);
//        assertThat(fromDb.getName()).isEqualTo("john");
//
//        verifyFindByIdIsCalledOnce();
//    }
//
//    @Test
//    public void whenInValidId_thenEmployeeShouldNotBeFound() {
//        Employee fromDb = employeeService.getEmployeeById(-99L);
//        verifyFindByIdIsCalledOnce();
//        assertThat(fromDb).isNull();
//    }
//
//    @Test
//    public void given3Employees_whengetAll_thenReturn3Records() {
//        Employee alex = new Employee("alex");
//        Employee john = new Employee("john");
//        Employee bob = new Employee("bob");
//
//        List<Employee> allEmployees = employeeService.getAllEmployees();
//        verifyFindAllEmployeesIsCalledOnce();
//        assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
//    }

    private void verifyFindByNameIsCalledOnce(String name) {
        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findByFirstName(name);
        Mockito.reset(employeeRepository);
    }

//    private void verifyFindByIdIsCalledOnce() {
//        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
//        Mockito.reset(employeeRepository);
//    }
//
//    private void verifyFindAllEmployeesIsCalledOnce() {
//        Mockito.verify(employeeRepository, VerificationModeFactory.times(1)).findAll();
//        Mockito.reset(employeeRepository);
//    }
}
