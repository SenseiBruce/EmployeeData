package com.example.springjpa.service;

import java.util.List;

import com.example.springjpa.entity.Projects;
import com.example.springjpa.exceptionhandlers.ProjectNotFoundException;


public interface ProjectsService {

	Projects add(String name);

	List<Projects> getByName(String name) throws ProjectNotFoundException;

	List<Projects> getAll();

	//Projects addEmployeeToProject(String empoyeeName, String projectName) ;

	long getCount();

	//Projects addEmployeeToProject(String employeeName, String projectName) throws ClientProtocolException, IOException;

}
