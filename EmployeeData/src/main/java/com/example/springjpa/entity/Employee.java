package com.example.springjpa.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee  {
	

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public EmployeeAccount getEmpoyeeAccount() {
		return empoyeeAccount;
	}


	public void setEmpoyeeAccount(EmployeeAccount empoyeeAccount) {
		this.empoyeeAccount = empoyeeAccount;
	}


	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	
	@OneToOne( cascade= CascadeType.ALL,orphanRemoval= true,fetch = FetchType.LAZY)
	private EmployeeAccount empoyeeAccount;


	public Employee(long i, String name, EmployeeAccount empoyeeAccount) {
		super();
		this.id = i;
		this.name = name;
		this.empoyeeAccount = empoyeeAccount;
	}


	public Employee() {
		
	}


	@Override
	public int hashCode() {
		return Objects.hash(empoyeeAccount, id, name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(empoyeeAccount, other.empoyeeAccount) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}


	
	
	
	

}
