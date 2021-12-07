package com.example.springjpa.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="employeeAccount")
public class EmployeeAccount {
	
	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, employee, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeAccount other = (EmployeeAccount) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(employee, other.employee)
				&& Objects.equals(id, other.id);
	}

	/*
	 * @OneToOne(cascade = CascadeType.ALL) private Employee employee;
	 */
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@Column
	//@Range(max=20,min = 3, message = "{account.number.invalid}")
	//@NotEmpty(message = "{Pease enter account number.}")
	private Long accountNumber;
	
	
	@OneToOne(cascade= CascadeType.ALL	, orphanRemoval= true)
	Employee employee;
	
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
}
