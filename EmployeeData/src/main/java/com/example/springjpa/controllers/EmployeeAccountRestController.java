package com.example.springjpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.entity.EmployeeAccount;
import com.example.springjpa.repository.EmployeeAccountRepository;
import com.example.springjpa.service.EmployeeAccountService;

@RestController
@RequestMapping("/")
public class EmployeeAccountRestController {

	
	@Autowired
	EmployeeAccountService employeeAccountService;
	
	@GetMapping("/employeeAccount")
	public List<EmployeeAccount> getAllEmployeeAccounts(){
		
		
		return employeeAccountService.getAll();
	}

	@GetMapping("/employeeAccount/{employeeName}")
	public List<EmployeeAccountRepository> getEmployeeAccountByEmployeeName(@PathVariable String employeeName ){
		
		return employeeAccountService.getByEmployeeName(employeeName);
	}
}
