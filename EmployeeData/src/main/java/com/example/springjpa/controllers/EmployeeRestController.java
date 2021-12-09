package com.example.springjpa.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.http.client.ClientProtocolException;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.exceptionhandlers.EmployeeNotFoundException;
import com.example.springjpa.exceptionhandlers.ResourceAlreadyExistsException;
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


	
	  @RequestMapping(value="/employee/save/{name}/", method = RequestMethod.POST)
	  
	  public Employee saveEmpoyee(@PathVariable String name) throws ResourceAlreadyExistsException {
		  Employee emp;
	  try {
		  emp = employeeService.save(name);
	  }
	  catch(Exception e) {
		  e.printStackTrace();
		  throw new ResourceAlreadyExistsException("The resource you are trying to add already exists in the system.");
	  }
		  
	  
	return emp ;
	  }

	  @RequestMapping(value="/employee/{employeeName}/addTo/project/{projectName}", method = RequestMethod.POST)
	  
	  public Employee tagEmpoyeetoProject(@PathVariable String employeeName, @PathVariable String projectName) 
			  throws ResourceAlreadyExistsException, ClientProtocolException, IOException, ParseException, EmployeeNotFoundException {
		  
		  Employee employee;
		if(employeeService.getByName(employeeName).isEmpty()||employeeService.getByName(employeeName).size()==0) {
				throw new EmployeeNotFoundException("Employee not found");
				}
			else {
			 employee = employeeService.getByName(employeeName).get(0);
			}
		  return employeeService.tagEmployeeToProject(employee,projectName);
		  
		  
	  }
	
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

