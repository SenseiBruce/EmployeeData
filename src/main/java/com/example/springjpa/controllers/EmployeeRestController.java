package com.example.springjpa.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.tomcat.util.json.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.exceptionhandlers.ConnectionToProjectsModuleRefusedExcetion;
import com.example.springjpa.exceptionhandlers.EmployeeNotFoundException;
import com.example.springjpa.exceptionhandlers.ResourceAlreadyExistsException;
import com.example.springjpa.service.EmployeeAccountService;
import com.example.springjpa.service.EmployeeService;
import com.example.springjpa.serviceimpl.PostMessageToOutboundQueueServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

//mer conflict demo
@RestController
@RequestMapping("/")
public class EmployeeRestController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeRestController.class);



	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeAccountService employeeAccountService;
	
	
	/*
	 * @RequestMapping(value="/hello", method = RequestMethod.GET) public String
	 * hello() {
	 * 
	 * 
	 * return "Hello World."; }
	 */
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

	  GsonBuilder builder = new GsonBuilder(); 
      //builder.setPrettyPrinting(); 
      
      
	  private Gson gson= new Gson();
	  
	  @Autowired
	  PostMessageToOutboundQueueServiceImpl producerAsRestEndpoint;


	  @Autowired
	  @Qualifier("jdbcScheduler")
	  private Scheduler jdbcscheduler;
	  
	  @RequestMapping(value="/employee/save/{name}/", method = RequestMethod.POST)
	  
	  public Employee saveEmpoyee(@PathVariable String name) throws ResourceAlreadyExistsException {
		  Employee emp = new Employee();
	  try {
		  emp = employeeService.save(name);
		  producerAsRestEndpoint.sendEmployeeToOutBoundQueue(emp);
		  
		  //employeeAccountService.addEmployeeAccount(emp);
	  }
	  catch(DataIntegrityViolationException e) {
		  e.printStackTrace();
		  throw new ResourceAlreadyExistsException("The resource you are trying to add already exists in the system.");
	  }

	return emp ;
	  }
	  
 @RequestMapping(value="/employee/save/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	  
	  public Employee saveEmpoyeeWhenObjectRecived(@RequestBody Employee employee) throws ResourceAlreadyExistsException {
		  Employee emp = new Employee();
	  try {
		  LOGGER.info("Starting to save employee.");
		  emp=employeeService.save(employee);
		 
	  }
	  catch(DataIntegrityViolationException e) {
		  e.printStackTrace();
		  throw new ResourceAlreadyExistsException("The resource you are trying to add already exists in the system.");
	  }
	 
	return emp ;
	  }
	  

	  @RequestMapping(value="/employee/{employeeName}/addTo/project/{projectName}", method = RequestMethod.POST)
	  
	  public Employee tagEmpoyeetoProject(@PathVariable String employeeName, @PathVariable String projectName) 
			  throws ResourceAlreadyExistsException, ClientProtocolException, IOException, ParseException, EmployeeNotFoundException
			  ,HttpHostConnectException, ConnectionToProjectsModuleRefusedExcetion {
		  
		  List<Employee> employee =employeeService.getByName(employeeName);
		if(employee.isEmpty()||employee.size()==0) {
				throw new EmployeeNotFoundException("Employee not found");
				}
			
		return employeeService.tagEmployeeToProjectUsingHttpClient(employee.get(0),projectName);
		  
		  
		  
	  }
	  
@RequestMapping(value="/employee/resatemplate/{employeeName}/addTo/project/{projectName}", method = RequestMethod.POST)
	  
	  public Employee tagEmpoyeetoProjectUsingRestTemplate(@PathVariable String employeeName, @PathVariable String projectName) 
			  throws ResourceAlreadyExistsException, ClientProtocolException, IOException, ParseException, EmployeeNotFoundException
			  ,HttpHostConnectException, ConnectionToProjectsModuleRefusedExcetion {
		  
		  List<Employee> employee =employeeService.getByName(employeeName);
		if(employee.isEmpty()||employee.size()==0) {
				throw new EmployeeNotFoundException("Employee not found");
				}
			else {
			// employee = employeeService.getByName(employeeName);
			}
		return employeeService.tagEmployeeToProjectUsingRestTemplate(employee.get(0),projectName);
		  
		  
		  
	  }
@RequestMapping(value="/employee/webClient/{employeeName}/addTo/project/{projectName}", method = RequestMethod.POST)

public Employee tagEmpoyeetoProjectUsingWebClient(@PathVariable String employeeName, @PathVariable String projectName) 
		  throws ResourceAlreadyExistsException, ClientProtocolException, IOException, ParseException, EmployeeNotFoundException
		  ,HttpHostConnectException, ConnectionToProjectsModuleRefusedExcetion {
	  
	  List<Employee> employee =employeeService.getByName(employeeName);
	if(employee.isEmpty()||employee.size()==0) {
			throw new EmployeeNotFoundException("Employee not found");
			}
		else {
		// employee = employeeService.getByName(employeeName);
		}
	return employeeService.tagEmployeeToProjectUsingWebClient(employee.get(0),projectName);
	  
	  
	  
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
	
	
	//Reactive Endpoints
	  
	  @GetMapping(value="/reactive/employee/count")
		public long getEmployeeCountRx() {
			return employeeService.findAll().toStream().count();
		}
	
	
	  @GetMapping(value="/reactive/employee")//,produces = {"application/xml","text/xml"}
	  public Flux<Employee> getAllEmpoyeesRx() {
	  
	  return  employeeService.findAll();
	  
	  }
	  
	  
	  @RequestMapping(value="/reactive/employee/getAllByProcedure",method = RequestMethod.GET)//,produces = "application/xml
	  public Optional<List<Employee>> getAllEmpoyeesByProcedureRx() {
		
	  return  Optional.ofNullable(employeeService.getByProcedureAll());
	  
	  }
	
	  @RequestMapping(value = "/reactive/employee/lastXEmplloyed/{top}",method = RequestMethod.GET)
	  public Optional<Flux<Employee>> getTopXIdEmployeeRx(@PathVariable int top){
		// List<Employee> list = employeeService.getAll();	
		 //list.stream().sorted().skip(top);
		 return Optional.ofNullable(employeeService.getTopXEmpoyeesRx(top));
		/*
		 * return Optional.ofNullable(list.stream()
		 * .sorted(Comparator.comparingDouble(Employee::getId).reversed()).limit(top));
		 */
	  }

	  @RequestMapping(value = "/reactive/employee/firstXEmployed/{last}",method = RequestMethod.GET)
	  public Optional<Flux<Employee>> getLastXIdEmployeeRx(@PathVariable int last){
		// List<Employee> list = employeeService.getAll();	
		 //list.stream().sorted().skip(top);
		 return Optional.ofNullable(employeeService.getLastXEmpoyeesRx(last));
			/*
			 * return Optional.ofNullable(list.stream()
			 * .sorted(Comparator.comparingDouble(Employee::getId)).limit(last));
			 */
	  }
	
	  
	  @RequestMapping(value="/reactive/employee/save/", method = RequestMethod.POST,consumes = "application/json")
	 	  
	 	  public Mono<Employee> saveEmpoyeeWhenObjectRecivedRx(@RequestBody Employee employee) 
	 			  throws ResourceAlreadyExistsException {
	 		 
	 		  Mono<Employee> mono = Mono.empty();
	 	  try {
	 		  LOGGER.info("Starting to save employee.");
	 		  mono=employeeService.saveRx(employee);
	 		 
	 	  }
	 	  catch(DataIntegrityViolationException e) {
	 		  e.printStackTrace();
	 		  throw new ResourceAlreadyExistsException("The resource you are trying to add already exists in the system.");
	 	  }
	 	 
	 	return mono ;
	 	  }
	 	  

	 	
	 	@RequestMapping(value="/reactive/employee/{name}",method = RequestMethod.GET)
	 	public Flux<Employee> getEmplyeeByNameRx(@PathVariable String name ) throws EmployeeNotFoundException{
	 		
	 		if(employeeService.getByName(name).isEmpty()||employeeService.getByName(name).size()==0) {
	 			throw new EmployeeNotFoundException("Employee not found");
	 			}
	 		else {
	 		return employeeService.getByNameRx(name);
	 		}
	 	}
	 	
}

	