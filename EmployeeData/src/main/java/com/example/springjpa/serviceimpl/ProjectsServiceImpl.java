package com.example.springjpa.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springjpa.entity.Employee;
import com.example.springjpa.entity.Projects;
import com.example.springjpa.exceptionhandlers.EmployeeNotFoundException;
import com.example.springjpa.repository.EmployeeRepository;
import com.example.springjpa.repository.ProjectsRepository;
import com.example.springjpa.service.ProjectsService;

@Service
public class ProjectsServiceImpl implements ProjectsService {

	@Autowired
	ProjectsRepository projectsRepository; 
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public Projects add(String name) {
		Projects project = new Projects();
		project.setProjectName(name);
		return projectsRepository.save(project);
	}

	@Override
	public List<Projects> getByName(String name) {
		
		return projectsRepository.findByProjectName(name);
	}

	@Override
	public List<Projects> getAll() {

		return projectsRepository.findAll();
	}

	@Override
	public Projects addEmployeeToProject(String employeeName, String projectName) throws EmployeeNotFoundException {
		Projects project = new Projects();
		project.setProjectName(projectName);
		
		List<Employee> emp =  employeeRepository.findByName(employeeName) ;
		if(emp.isEmpty()) {
			throw new EmployeeNotFoundException("This employee does not exist.");
		
		}
		else {
			

			project.setEmployee(emp.get(0));
		}
		
			
		return projectsRepository.save(project);
	}

}
