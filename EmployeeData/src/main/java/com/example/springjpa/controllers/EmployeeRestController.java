package com.example.springjpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.exceptionhandlers.EmployeeNotFoundException;
import com.example.springjpa.service.EmployeeService;

//mer conflict demo
@RestController
public class EmployeeRestController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World.";
	}
	
	  @RequestMapping("/Employee")
	  public Page<Employee> getAllEmpoyees(Pageable pageable) {
	  
	  return  employeeService.getAll(pageable);
	  
	  }

	 //line 27 chaange from locally and central merged


	/*
	 * @RequestMapping("/saveEmployee/{name}/")
	 * 
	 * public Employee saveEmpoyee(@PathVariable String name) {
	 * 
	 * return employeeService.save(name); }
	 */
	
	@RequestMapping("/Employee/{name}")
	public List<Employee> getEmplyeeById(@PathVariable String name ) throws EmployeeNotFoundException{
		
		if(employeeService.getByName(name)==null) {
			throw new EmployeeNotFoundException("Name "+name);
		}
		return employeeService.getByName(name);
	}
	
	
	
}

