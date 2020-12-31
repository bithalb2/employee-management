package com.subhra.employeemanagement.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.subhra.employeemanagement.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(Integer empId);
	
	void deleteEmployee(Integer empId);
	
	Employee updateEmployee(Employee employee);
	
	Employee addEmployee(Employee employee);
	
	/**	Method for only Pagination
	/* Page<Employee> getEmployeesByPage(int pageNumber);
	*/
	// Method for Pagination as well as Sorting
	Page<Employee> getEmployeesByPage(int pageNumber, String sortField, String sortOrder);
}
