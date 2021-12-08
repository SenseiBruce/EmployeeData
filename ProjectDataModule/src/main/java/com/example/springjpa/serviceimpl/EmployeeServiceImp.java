package com.example.springjpa.serviceimpl;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.entity.EmployeeAccount;
import com.example.springjpa.exceptionhandlers.ResourceAlreadyExistsException;
import com.example.springjpa.repository.EmployeeAccountRepository;
import com.example.springjpa.repository.EmployeeRepository;
import com.example.springjpa.service.EmployeeService;

@Service
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository ;

	@Autowired
	EmployeeAccountRepository employeeAccountRepository ;
	
	@Override
	public List<Employee> getByName(String name) {
		
		return employeeRepository.findByName(name);
	}

	/*
	 * @Override public List<Employee> getAll() {
	 * 
	 * return employeeRepository.findAll(); }
	 */

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> getTopXEmpoyees(int x) {
		// TODO Auto-generated method stub
		return employeeRepository.getTopXEmpoyees(x);
	}
	
	@Override
	public List<Employee> getLastXEmpoyees(int x) {
		// TODO Auto-generated method stub
		return employeeRepository.getLastXEmpoyees(x);
	}

	@Override
	public List<Employee> getByProcedureAll() {
		// TODO Auto-generated method stub
		return employeeRepository.getAllEmployeesWithProcedure();
	}

	@Override
	public Employee save(String name) throws ResourceAlreadyExistsException {
		Employee  
		emp =employeeRepository.save(new Employee(0, name, null));
		
		employeeAccountRepository.save(new EmployeeAccount(null,(emp.getId()*2) , emp));
				
		return emp;
	}
	
	
}
