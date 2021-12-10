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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.entity.EmployeeAccount;
import com.example.springjpa.exceptionhandlers.ConnectionToProjectsModuleRefusedExcetion;
import com.example.springjpa.exceptionhandlers.ResourceAlreadyExistsException;
import com.example.springjpa.model.Projects;
import com.example.springjpa.repository.EmployeeAccountRepository;
import com.example.springjpa.repository.EmployeeRepository;
import com.example.springjpa.service.EmployeeService;
import com.google.gson.Gson;

@Service
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository ;

	@Autowired
	EmployeeAccountRepository employeeAccountRepository ;
	
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
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

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
		employeeRepository.flush();
		return emp;
	}

	@Override
	public Employee tagEmployeeToProject(Employee employee, String projectName) 
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
				  
				  Projects apiResponse =  new Gson().fromJson( responseString , Projects.class);
						      
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
	
	
}
