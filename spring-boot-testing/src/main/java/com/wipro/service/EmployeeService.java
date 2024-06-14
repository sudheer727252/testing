package com.wipro.service;

import java.util.List;

import com.wipro.entity.Employee;

public interface EmployeeService {

	public void deleteById(Integer id);

    public List<Employee> findAll() ;

    void deleteAll();

    public Employee save(Employee employee);
    
    public Employee getEmployeeByFirstName(String firstName);
}