package com.example.springjpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springjpa.entity.EmployeeAccount;

@Repository
public interface EmployeeAccountReactiveRepository extends CrudRepository<EmployeeAccount, Long> {
	
	
	
}
