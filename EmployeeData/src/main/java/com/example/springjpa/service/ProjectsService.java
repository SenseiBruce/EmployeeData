package com.example.springjpa.service;

import java.util.List;

import com.example.springjpa.entity.Projects;
import com.example.springjpa.exceptionhandlers.EmployeeNotFoundException;


public interface ProjectsService {

	Projects add(String name);

	List<Projects> getByName(String name);

	List<Projects> getAll();

	Projects addEmployeeToProject(String empoyeeName, String projectName) throws EmployeeNotFoundException;

}
