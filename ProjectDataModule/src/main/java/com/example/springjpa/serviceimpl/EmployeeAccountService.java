package com.example.springjpa.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.entity.EmployeeAccount;
import com.example.springjpa.repository.EmployeeAccountRepository;
import com.example.springjpa.repository.EmployeeRepository;
import com.example.springjpa.service.EmployeeService;

@Service
public class EmployeeAccountService implements com.example.springjpa.service.EmployeeAccountService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeAccountRepository employeeAccountRepository; 
	
	@Override
	public List<EmployeeAccount> getAll() {
		
		return employeeAccountRepository.findAll();
	}

	/*
	 * @Override public List<EmployeeAccountRepository> getByEmployeeName(String
	 * employeeName) {
	 * 
	 * return employeeAccountRepository.getByEmployeeName(employeeName); }
	 */

	
	@Override
	public List<EmployeeAccountRepository> getByEmployeeName(String name) {
		// TODO Auto-generated method stub
		return employeeAccountRepository.getByEmployeeName(name) ;
	}

}
