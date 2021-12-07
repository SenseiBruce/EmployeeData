package com.example.springjpa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.exceptionhandlers.EmployeeNotFoundException;
import com.example.springjpa.service.EmployeeService;

//mer conflict demo
@RestController
@RequestMapping("/")
public class EmployeeRestController {

	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public String hello() {
		return "Hello World.";
	}
	
	@RequestMapping(value="/employee/count",method = RequestMethod.GET)
	public long getEmployeeCount() {
		return employeeService.getAll().stream().count();
	}
	
	  @RequestMapping(value="/employee",method = RequestMethod.GET)//,produces = {"application/xml","text/xml"}
	  public Optional<List<Employee>> getAllEmpoyees() {
	  
	  return  Optional.ofNullable(employeeService.getAll());
	  
	  }
	  
	  @RequestMapping(value="/employee/getAllByProcedure",method = RequestMethod.GET)//,produces = "application/xml
	  public Optional<List<Employee>> getAllEmpoyeesByProcedure() {
	  
	  return  Optional.ofNullable(employeeService.getByProcedureAll());
	  
	  }
	  
	  @RequestMapping(value = "/employee/lastXEmplloyed/{top}",method = RequestMethod.GET)
	  public Optional<List<Employee>> getTopXIdEmployee(@PathVariable int top){
		// List<Employee> list = employeeService.getAll();	
		 //list.stream().sorted().skip(top);
		 return Optional.ofNullable(employeeService.getTopXEmpoyees(top));
		/*
		 * return Optional.ofNullable(list.stream()
		 * .sorted(Comparator.comparingDouble(Employee::getId).reversed()).limit(top));
		 */
	  }

	  @RequestMapping(value = "/employee/firstXEmployed/{last}",method = RequestMethod.GET)
	  public Optional<List<Employee>> getLastXIdEmployee(@PathVariable int last){
		// List<Employee> list = employeeService.getAll();	
		 //list.stream().sorted().skip(top);
		 return Optional.ofNullable(employeeService.getLastXEmpoyees(last));
			/*
			 * return Optional.ofNullable(list.stream()
			 * .sorted(Comparator.comparingDouble(Employee::getId)).limit(last));
			 */
	  }
	 //line 27 chaange from locally and central merged


	/*
	 * @RequestMapping("/saveEmployee/{name}/")
	 * 
	 * public Employee saveEmpoyee(@PathVariable String name) {
	 * 
	 * return employeeService.save(name); }
	 */
	
	@RequestMapping(value="/employee/{name}",method = RequestMethod.GET)
	public List<Employee> getEmplyeeByName(@PathVariable String name ) throws EmployeeNotFoundException{
		
		if(employeeService.getByName(name).isEmpty()||employeeService.getByName(name).size()==0) {
			throw new EmployeeNotFoundException("Employee not found");
			}
		else {
		return employeeService.getByName(name);
		}
	}
	
	
	
}

