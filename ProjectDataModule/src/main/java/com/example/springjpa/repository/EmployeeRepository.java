package com.example.springjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springjpa.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
	public List<Employee> findByName(String name);
	
	public List<Employee> findAll();

	public Employee getByName(String name);

	@Query(value="select top (?) * from Employee order by id desc",nativeQuery=true)
	public List<Employee> getTopXEmpoyees(int x);
	
	@Query(value="select top (?) * from Employee order by id asc",nativeQuery=true)
	public List<Employee> getLastXEmpoyees(int x);
	
	
	 @Query(value="get_all_with_names_starting_with_a",nativeQuery=true)
	 public List<Employee> getAllEmployeesWithProcedure();
	 
	/*
	 * @Procedure public List<Employee> get_all_with_names_starting_with_a();
	 */
}
