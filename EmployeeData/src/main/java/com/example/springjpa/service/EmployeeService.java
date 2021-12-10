package com.example.springjpa.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Service;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.exceptionhandlers.ConnectionToProjectsModuleRefusedExcetion;
import com.example.springjpa.exceptionhandlers.ResourceAlreadyExistsException;

@Service
public interface EmployeeService {

	/*
	 * List<Employee> getAll();
	 * 
	 * Employee save(String name);
	 * 
	 * void delete(Long id);
	 */

	List<Employee> getByName(String name);

	List<Employee> getAll();
	
	List<Employee> getTopXEmpoyees(int x);

	List<Employee> getLastXEmpoyees(int x);

	List<Employee> getByProcedureAll();

	Employee save(String name) throws ResourceAlreadyExistsException;

	Employee tagEmployeeToProject(Employee employee, String projectName) throws ClientProtocolException, IOException
	, ParseException,HttpHostConnectException, ConnectionToProjectsModuleRefusedExcetion;

}
