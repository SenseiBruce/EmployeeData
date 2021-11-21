package com.example.springjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.entity.EmployeeAccount;

@Repository
public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccount, Long> {

	//List<EmployeeAccountRepository> getByEmployeeName(String employeeName);

	List<EmployeeAccountRepository> getByEmployee(Employee employee);
	
	@Query(value="select ea.* from employee_account as ea  left join employee as e on ea.employee_id=e.id where e.name=?1",nativeQuery=true)
	List<EmployeeAccountRepository> getByEmployeeName(String name);

}
