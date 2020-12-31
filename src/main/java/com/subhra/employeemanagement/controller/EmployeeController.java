package com.subhra.employeemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.subhra.employeemanagement.model.Employee;
import com.subhra.employeemanagement.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employees")
	public String getAllEmployees(Model model) {
		/*	NORMAL VIEW
		 * model.addAttribute("employees", employeeService.getAllEmployees()); return
		 * "index";
		 */
		//	PAGINATED VIEW
		//return getEmployeesByPage(1, "firstName", "asc", model);
		
		// Pagination and Sorting view
		return getEmployeesByPage(1, "firstName", "asc", model);
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
	
	/** Method for only Pagination
	 * @RequestMapping(path = "/page/{pageNumber}") public String
	 * getEmployeesByPage(@PathVariable(name = "pageNumber") Integer pageNumber,
	 * Model model) { Page<Employee> page =
	 * employeeService.getEmployeesByPage(pageNumber);
	 * 
	 * model.addAttribute("currentPage", pageNumber);
	 * model.addAttribute("totalPages", page.getTotalPages());
	 * model.addAttribute("totalEmployees", page.getTotalElements());
	 * model.addAttribute("employees", page.getContent()); return "index"; }
	 */
	
	@RequestMapping(path = "/page/{pageNumber}")
	public String getEmployeesByPage(@PathVariable(name = "pageNumber") Integer pageNumber, 
			@RequestParam(name = "sortField") String sortField,
			@RequestParam(name = "sortOrder") String sortOrder,
			Model model) {
		Page<Employee> page = employeeService.getEmployeesByPage(pageNumber, sortField, sortOrder);
		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalEmployees", page.getTotalElements());
		model.addAttribute("employees", page.getContent());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortOrder", sortOrder);
		model.addAttribute("reverseSortOrder", (sortOrder.equalsIgnoreCase("asc")) ? "desc" : "asc");
		return "index";
	}
}
