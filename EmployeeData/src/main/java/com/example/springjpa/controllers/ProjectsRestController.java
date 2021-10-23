package com.example.springjpa.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjpa.entity.Projects;
import com.example.springjpa.service.ProjectsService;

@RestController
public class ProjectsRestController {
	
	@Autowired
	ProjectsService projectsService;
	
	@PostMapping("/addProject/{name}")
	public Projects add(@PathVariable String name) {
		
		
		return projectsService.add(name);
		
	}
	
	@GetMapping("/getProject/{name}")
	public List<Projects> getProjectByName(@PathVariable String name){
		
		return projectsService.getByName(name);
	}
	
	@GetMapping("/getProjects")
	public List<Projects> getAllProjects(){
		
		return projectsService.getAll();
	}

	
	@PostMapping("/addEmployeeToProject/{employeeName}/{projectName}")
	public Optional<Projects> addEmpoyeeToProject(@PathVariable String employeeName, @PathVariable String projectName) {
		
		return Optional.ofNullable(projectsService.addEmployeeToProject(employeeName,projectName));
	}
	
	
}
