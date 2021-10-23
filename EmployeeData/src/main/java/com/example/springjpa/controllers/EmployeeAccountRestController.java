package com.example.springjpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.repository.EmployeeAccountRepository;
import com.example.springjpa.service.EmployeeAccountService;

@RestController
public class EmployeeAccountRestController {

	
	@Autowired
	EmployeeAccountService employeeAccountService;
	
	@GetMapping
	public List<EmployeeAccountRepository> getAllEmployeeAccounts(){
		
		
		return employeeAccountService.getAll();
	}
}
