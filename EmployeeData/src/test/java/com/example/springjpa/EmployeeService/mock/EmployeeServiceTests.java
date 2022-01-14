package com.example.springjpa.EmployeeService.mock;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.service.EmployeeService;

@RunWith(SpringRunner.class)
public class EmployeeServiceTests {

	
	@MockBean 
	EmployeeService employeeService;
	
	@Test
	public void ensureGetAllMethodReturndsEmployeeObjects() {
		when(employeeService.getAll()).thenReturn(Stream.of(new Employee(0, "Kinshuk", null)).collect(Collectors.toList()));
		
		assertEquals(1,employeeService.getAll().size());
		assertEquals("Kinshuk",employeeService.getAll().get(0).getName());
		assertNotNull(employeeService.getAll());
		assertEquals(true,employeeService.getAll().get(0) instanceof Employee);
	}
	
}
