package com.example.springjpa.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.entity.EmployeeAccount;
import com.example.springjpa.repository.EmployeeAccountRepository;
import com.example.springjpa.repository.EmployeeRepository;
import com.example.springjpa.service.EmployeeAccountService;

@Service
public class EmployeeAccountServiceImpl implements EmployeeAccountService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeAccountRepository employeeAccountRepository; 
	

	
	@Override
	public List<EmployeeAccountRepository> getByEmployeeName(String name) {
	
		return employeeAccountRepository.getByEmployeeName(name) ;
	}

	@Override
	public EmployeeAccount addEmployeeAccount(Employee emp) {
		
		return employeeAccountRepository.save(new EmployeeAccount(null, emp.getId()*3, emp));
	}

	@Override
	public List<EmployeeAccount> getAll() {
		
		return employeeAccountRepository.findAll();
	}

}
