package com.example.springjpa.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.repository.EmployeeRepository;
import com.example.springjpa.service.EmployeeService;

@Service
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository ;

	
	@Override
	public List<Employee> getByName(String name) {
		
		return employeeRepository.findByName(name);
	}

	@Override
	public List<Employee> getAll() {
		
		return employeeRepository.findAll();
	}
	
	
}
