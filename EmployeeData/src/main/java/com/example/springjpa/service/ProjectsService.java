package com.example.springjpa.service;

import java.util.List;

import com.example.springjpa.entity.Projects;


public interface ProjectsService {

	Projects add(String name);

	List<Projects> getByName(String name);

	List<Projects> getAll();

	Projects addEmployeeToProject(String empoyeeName, String projectName);

}
