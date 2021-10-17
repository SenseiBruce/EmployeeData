package com.example.springjpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	
	  @RequestMapping("/getAllEmployees")
	  public List<Employee> getAllEmpoyees() {
	  
	  return employeeService.getAll();
	  
	  }

	 //line 27 chaange from locally and central merged


	/*
	 * @RequestMapping("/saveEmployee/{name}/")
	 * 
	 * public Employee saveEmpoyee(@PathVariable String name) {
	 * 
	 * return employeeService.save(name); }
	 */
	
	@RequestMapping("/getEmployeeByName/{name}")
	public List<Employee> getEmplyeeById(@PathVariable String name ) throws EmployeeNotFoundException{
		
		if(employeeService.getByName(name)==null) {
			throw new EmployeeNotFoundException("Name "+name);
		}
		return employeeService.getByName(name);
	}
	
	
	
}

