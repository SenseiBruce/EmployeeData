package com.example.springjpa.serviceimpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.util.json.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.entity.EmployeeAccount;
import com.example.springjpa.exceptionhandlers.ConnectionToProjectsModuleRefusedExcetion;
import com.example.springjpa.exceptionhandlers.ResourceAlreadyExistsException;
import com.example.springjpa.model.Project;
import com.example.springjpa.repository.EmployeeAccountRepository;
import com.example.springjpa.repository.EmployeeRepository;
import com.example.springjpa.service.EmployeeService;
import com.google.gson.Gson;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class EmployeeServiceImp implements EmployeeService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(EmployeeServiceImp.class);
	
	@Autowired
	EmployeeRepository employeeRepository ;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	EmployeeAccountRepository employeeAccountRepository ;
	
	
	@Autowired
	@Qualifier("jdbcScheduler")
	private Scheduler jdbcScheduler;

	
	@Override
	public List<Employee> getByName(String name) {
		employeeRepository.findByName(name);
		return employeeRepository.findByName(name);
	}

	/*
	 * @Override public List<Employee> getAll() {
	 * 
	 * return employeeRepository.findAll(); }
	 */

	@Override
	public List<Employee> getAll() {

		return employeeRepository.findAll();
	}

	/*
	 * @Override public Flux<Employee> getAllStream() {
	 * 
	 * return reactiveEmployeeRepository.findAll(); }
	 */

	@Override
	public List<Employee> getTopXEmpoyees(int x) {
		// TODO Auto-generated method stub
		return employeeRepository.getTopXEmpoyees(x);
	}
	
	@Override
	public List<Employee> getLastXEmpoyees(int x) {
		// TODO Auto-generated method stub
		return employeeRepository.getLastXEmpoyees(x);
	}

	@Override
	public List<Employee> getByProcedureAll() {
		// TODO Auto-generated method stub
		return employeeRepository.getAllEmployeesWithProcedure();
	}

	@Override
	public Employee save(String name) throws ResourceAlreadyExistsException {
		Employee  
		emp =employeeRepository.save(new Employee(0, name, null));
		
		EmployeeAccount employeeAccount = employeeAccountRepository.save(new EmployeeAccount(null,(emp.getId()*2) , emp));
		emp.setEmpoyeeAccount(employeeAccount);
		employeeRepository.save(emp);
		return emp;
	}
	
	@Override
	public Employee save(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	@Override
	public Employee tagEmployeeToProjectUsingHttpClient(Employee employee, String projectName) 
			throws ClientProtocolException, IOException, ParseException, HttpHostConnectException, ConnectionToProjectsModuleRefusedExcetion {
		
			  
		  
			  HttpUriRequest
			  request = new HttpGet( "http://localhost:8087//project//"+projectName );
			   
			  Long projectId=null;
			try {
				  CloseableHttpResponse response = HttpClientBuilder.create().build().execute(
			  request );
				  HttpEntity entity = response.getEntity();
				  
				  String res = null;
				  
				  InputStream instream = entity.getContent();
				  
				  byte[] bytes = IOUtils.toByteArray(instream);
				 
				  res = new String(bytes, "UTF-8");
				  
				  String responseString = res.substring(1,res.length()-1);
				  
				  instream.close(); 
				  
				  Project apiResponse =  new Gson().fromJson( responseString , Project.class);
						      
				  projectId = apiResponse.getId();
				  
				  
				  
			  }
			  catch(HttpHostConnectException e) {
				  throw new ConnectionToProjectsModuleRefusedExcetion("No response recived from the Projects module,"
				  		+ " please try again after some time "
				  		+ "If the issue persists please reach out to application Support Team.");
			  }
			  
			  
			  
			  employee.setProjectid(projectId);
			   
			  
		return employeeRepository.save(employee);
	}

	
	@Override
	public Employee tagEmployeeToProjectUsingRestTemplate(Employee employee, String projectName) {
		//Getting the project id from Project web service.
		Project[] project = restTemplate.getForObject(
				"http://localhost:8087//reactive//project//"+projectName, Project[].class);
		
		Long projectId=project[0].getId();
		employee.setProjectid(projectId);
		for(Project p : project)
		LOGGER.info(p.toString());
		LOGGER.info(project[0].getId().toString());
		return employeeRepository.save(employee);
	}

	//Reactively returning the response// start
	@Override
	public Flux<Employee> findAll() {
		Flux<Employee> defer = Flux.defer(() -> Flux.fromIterable(this.employeeRepository.findAll()));
		return defer.subscribeOn(jdbcScheduler);
	}

	@Override
	public Flux<Employee> getByProcedureAllReactively() {
		Flux<Employee> defer = Flux.defer(()-> Flux.fromIterable(this.employeeRepository.getAllEmployeesWithProcedure()));
		return defer.subscribeOn(jdbcScheduler);
	}

	@Override
	public Flux<Employee> getTopXEmpoyeesRx(int top) {
		Flux<Employee> defer = Flux.defer(()-> Flux.fromIterable(this.employeeRepository.getTopXEmpoyees(top)));
		return defer.subscribeOn(jdbcScheduler);
	}

	@Override
	public Flux<Employee> getLastXEmpoyeesRx(int last) {
		Flux<Employee> defer= Flux.defer(()->Flux.fromIterable(this.employeeRepository.getLastXEmpoyees(last)));
		return defer.subscribeOn(jdbcScheduler);
	}

	@Override
	public Mono<Employee> saveRx(Employee employee) {
		Mono<Employee> just= Mono.just(this.employeeRepository.save(employee));
		return just.subscribeOn(jdbcScheduler);
	}

	@Override
	public Flux<Employee> getByNameRx(String name) {
		Flux<Employee> just= Flux.just(this.employeeRepository.getByName(name));
		return just.subscribeOn(jdbcScheduler);
	}

	
	
	
	
	//Reactively returning the response// end
	
	@Autowired
	WebClient webClient;
	
	@Override
	public Employee tagEmployeeToProjectUsingWebClient(Employee employee, String projectName) {

		//Getting the project id from Project web service.
		Flux<Project> project = webClient.get().uri("/reactive/project/"+projectName).retrieve().bodyToFlux
				(Project.class);
		
		Mono<Project> mono=project.next();
		Long projectId=mono.block().getId();
		employee.setProjectid(projectId);
		
		LOGGER.info(mono.block().toString());
		LOGGER.info(projectId.toString());
		return employeeRepository.save(employee);
	
	}

	
	
}
