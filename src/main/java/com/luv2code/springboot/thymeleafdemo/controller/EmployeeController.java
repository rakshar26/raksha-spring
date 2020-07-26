package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService=theEmployeeService;
	}
		
	//add mappings for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		//get employees from DB
		List<Employee> theEmployees = employeeService.findAll();
		//add to spring Model
		theModel.addAttribute("employees",theEmployees);
		return "employees/list-employees";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showAdd(Model theModel) {
		//create modle attribute to bind form data
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee",theEmployee);
		
		return "employees/employee-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showUpdate(@RequestParam("employeeId") int theId,
			Model theModel) {
		
		//get the employee from service
		Employee theEmployee = employeeService.findById(theId);
		//set employee a sa modle attribute to prepopulate the form
		theModel.addAttribute("employee",theEmployee);
		//send over to our form
		return  "employees/employee-form";
		
		
	}
	
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		//save the employee
		employeeService.save(theEmployee);
		
		return "redirect:/employees/list";
	}
	
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		//delete the employee
		employeeService.deleteById(theId);
		
		//redirect to /employees/list
		return  "redirect:/employees/list";
		
		
	}
	
	
}