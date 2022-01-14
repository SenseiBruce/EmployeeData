package com.example.springjpa.model;

import java.util.Objects;


public class Projects {
	
	Long id;
	
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
