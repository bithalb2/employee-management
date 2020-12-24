package com.subhra.employeemanagement.service;

import java.util.List;

import com.subhra.employeemanagement.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(Integer empId);
	
	void deleteEmployee(Integer empId);
	
	Employee updateEmployee(Employee employee);
	
	Employee addEmployee(Employee employee);
}
