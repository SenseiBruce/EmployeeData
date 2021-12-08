package com.example.springjpa.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="projects",uniqueConstraints = { @UniqueConstraint(columnNames = { "projectName"}) })
public class Projects {

	
	@Override
	public int hashCode() {
		return Objects.hash(employee, id, projectName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projects other = (Projects) obj;
		return Objects.equals(employee, other.employee) && Objects.equals(id, other.id)
				&& Objects.equals(projectName, other.projectName);
	}

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id;
	
	@Column
	String projectName;
	
	@ManyToOne(cascade= CascadeType.ALL)	
	Employee employee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	
}
