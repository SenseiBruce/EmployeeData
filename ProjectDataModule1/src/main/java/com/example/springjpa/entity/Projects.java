package com.example.springjpa.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="projects",uniqueConstraints = { @UniqueConstraint(columnNames = { "projectName"}) })
public class Projects {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long id;
	
	@Column
	String projectName;
	
	
	
	public Projects(Long id, String projectName) {
		super();
		this.id = id;
		this.projectName = projectName;
		
	}
	public Projects() {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash( id, projectName);
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
		return  Objects.equals(id, other.id)
				&& Objects.equals(projectName, other.projectName);
	}

	
	
	
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

	

	
}
