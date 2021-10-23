package com.example.springjpa.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springjpa.entity.EmployeeAccount;
import com.example.springjpa.repository.EmployeeAccountRepository;

public class EmployeeAccountService implements com.example.springjpa.service.EmployeeAccountService {

	@Autowired
	EmployeeAccountRepository employeeAccountRepository; 
	
	@Override
	public List<EmployeeAccountRepository> getAll() {
		
		return employeeAccountRepository.findAll();
	}

}
