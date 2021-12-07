package com.example.springjpa.repository.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.repository.EmployeeRepository;
import com.example.springjpa.service.EmployeeService;
import com.example.springjpa.serviceimpl.EmployeeServiceImp;

@RunWith(SpringRunner.class)

public class EmployeeRepositoryMock {

	
	/*
	 * @Autowired EmployeeServiceImp employeeService;
	 */
	@MockBean
	EmployeeRepository employeeRepository;
	

	
	@Test
	public void getEmployeeTest() {
		when(employeeRepository.findAll()).thenReturn(Stream.of(new Employee(1, "Champ", null),new Employee(2, "Bruce", null)).collect(Collectors.toList()));
	
	assertEquals(2,employeeRepository.findAll().size());
	}
}
