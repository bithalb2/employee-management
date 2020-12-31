package com.subhra.employeemanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.subhra.employeemanagement.model.Employee;
import com.subhra.employeemanagement.repository.EmployeeRepository;
import com.subhra.employeemanagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private static final int PAGE_SIZE = 5;

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

	
	/**	Method for only Pagination
	 * @Override public Page<Employee> getEmployeesByPage(int pageNumber) { Pageable
	 * pageable = PageRequest.of(pageNumber - 1, PAGE_SIZE); return
	 * employeeRepository.findAll(pageable); }
	 */

	// Method for Pagination and Sorting
	@Override
	public Page<Employee> getEmployeesByPage(int pageNumber, String sortField, String sortOrder) {
		Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, PAGE_SIZE, sort);
		return employeeRepository.findAll(pageable);
	}
}
