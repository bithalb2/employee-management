package com.subhra.employeemanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subhra.employeemanagement.model.Employee;
import com.subhra.employeemanagement.repository.EmployeeRepository;
import com.subhra.employeemanagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Integer empId) {
		Optional<Employee> optional = employeeRepository.findById(empId);
		Employee employee = null;
		if(optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException();
		}
		return employee;
	}

	@Override
	public void deleteEmployee(Integer empId) {
		employeeRepository.deleteById(empId);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return addEmployee(employee);	
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);	
	}
}
