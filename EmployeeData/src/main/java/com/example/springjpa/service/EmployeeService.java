package com.example.springjpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springjpa.entity.Employee;

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

	Employee save(String name);

}
