package com.example.springjpa.service;

import java.util.List;

import com.example.springjpa.entity.Employee;

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

}
