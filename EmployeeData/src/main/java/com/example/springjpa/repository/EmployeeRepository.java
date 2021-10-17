package com.example.springjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springjpa.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
	public List<Employee> findByName(String name);
	
	public List<Employee> findAll();
	
	
}
