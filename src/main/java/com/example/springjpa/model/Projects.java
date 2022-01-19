package com.example.springjpa.model;

import java.util.List;

public class Projects {
	
	private List<Project> projects ;

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Projects(List<Project> projects) {
		super();
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Projects [projects=" + projects + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projects == null) ? 0 : projects.hashCode());
		return result;
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
		if (projects == null) {
			if (other.projects != null)
				return false;
		} else if (!projects.equals(other.projects))
			return false;
		return true;
	}
	

}
