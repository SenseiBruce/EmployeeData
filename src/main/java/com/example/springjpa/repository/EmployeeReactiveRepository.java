package com.example.springjpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springjpa.entity.Employee;

@Repository
public interface EmployeeReactiveRepository extends CrudRepository<Employee, Long> {

	
	
}
