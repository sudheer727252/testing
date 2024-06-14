package com.wipro.dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wipro.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	public abstract Employee findByFirstName(String firstName);
}