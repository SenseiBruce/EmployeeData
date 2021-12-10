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
import javax.persistence.UniqueConstraint;

@Entity

@Table(name="employeeAccount",uniqueConstraints = { @UniqueConstraint(columnNames = { "accountNumber"}) })
public class EmployeeAccount {
	
	public EmployeeAccount() {
	}
	
	


	public EmployeeAccount(Long id, Long accountNumber, Employee employee) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.employee = employee;
	}




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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long accountNumber;
	

	@OneToOne( cascade= CascadeType.ALL,orphanRemoval= true,fetch = FetchType.LAZY)
	private Employee employee;
	

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
