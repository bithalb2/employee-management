package com.subhra.employeemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.subhra.employeemanagement.model.Employee;
import com.subhra.employeemanagement.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employees")
	public String getAllEmployees(Model model) {
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "index";
	}
	
	@GetMapping("/employee")
	public String showAddEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "add_form";
	}
	
	@PostMapping("/employee")
	public String addEmployee(@ModelAttribute(name = "employee") Employee employee) {
		employeeService.addEmployee(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/employee/{empId}")
	public String showUpdateEmployeeForm(@PathVariable(name = "empId") Integer empId, Model model) {
		Employee employee = employeeService.getEmployeeById(empId);
		model.addAttribute("employee", employee);
		return "update_form";
	}
	
	@RequestMapping(path = "/deleteEmployee/{empId}")
	public String deleteEmployee(@PathVariable(name = "empId") Integer empId) {
		employeeService.deleteEmployee(empId);
		return "redirect:/employees";
	}
}
