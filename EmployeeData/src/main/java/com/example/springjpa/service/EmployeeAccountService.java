package com.example.springjpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.entity.EmployeeAccount;
import com.example.springjpa.repository.EmployeeAccountRepository;

@Service
public interface EmployeeAccountService {

	List<EmployeeAccount> getAll();

	//List<EmployeeAccountRepository> getByEmployeeName(String employeeName);

	//List<EmployeeAccountRepository> getByEmployee(Employee employee);

	List<EmployeeAccountRepository> getByEmployeeName(String name);

}
