package com.example.springjpa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.entity.Projects;
import com.example.springjpa.exceptionhandlers.EmployeeNotFoundException;
import com.example.springjpa.service.ProjectsService;

@RestController
public class ProjectsRestController {
	
	@Autowired
	ProjectsService projectsService;
	
	@PostMapping("/addProject/{name}")
	public Projects add(@PathVariable String name) {
		
		
		return projectsService.add(name);
		
	}
	
	@GetMapping("/Project/{name}")
	public List<Projects> getProjectByName(@PathVariable String name){
		
		return projectsService.getByName(name);
	}
	
	@GetMapping(value= "/Project",produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Projects> getAllProjects(){
		
		return projectsService.getAll();
	}

	
	@PostMapping("/Employee/Project/add/{employeeName}/{projectName}")
	public Optional<Projects> addEmpoyeeToProject(@PathVariable String employeeName, @PathVariable String projectName) throws EmployeeNotFoundException {
		
		return Optional.ofNullable(projectsService.addEmployeeToProject(employeeName,projectName));
	}
	
	
}
