package com.example.springjpa.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springjpa.entity.Projects;
import com.example.springjpa.exceptionhandlers.ProjectNotFoundException;
import com.example.springjpa.repository.ProjectsRepository;
import com.example.springjpa.service.ProjectsService;

@Service
public class ProjectsServiceImpl implements ProjectsService {

	@Autowired
	ProjectsRepository projectsRepository;

	@Override
	public Projects add(String name) {
		Projects project = new Projects(null, name);
		//project.setProjectName(name);
		return projectsRepository.save(project);
	}

	@Override
	public List<Projects> getByName(String name) throws ProjectNotFoundException {
		if (projectsRepository.findByProjectName(name).isEmpty()
				|| projectsRepository.findByProjectName(name).size() == 0) {
			throw new ProjectNotFoundException("Project not found.");
		}
		return projectsRepository.findByProjectName(name);
	}

	@Override
	public List<Projects> getAll() {
		return projectsRepository.findAll();
		// return projectsRepository.findAllProjectNames();
	}

	/*
	 * @Override public Projects addEmployeeToProject(String employeeName, String
	 * projectName) {
	 * 
	 * Projects project = new Projects(); project.setProjectName(projectName);
	 * 
	 * List<Employee> emp = employeeRepository.findByName(employeeName) ;
	 * if(emp.isEmpty()||emp.size()==0) { throw new
	 * EmployeeNotFoundException("This employee does not exist.");
	 * 
	 * } else {
	 * 
	 * 
	 * project.setEmployee(emp.get(0)); }
	 * 
	 * 
	 * return projectsRepository.save(project);
	 * 
	 * return null;}
	 */

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return projectsRepository.getCount();
	}
	/*
	 * @Override public Projects addEmployeeToProject(String employeeName, String
	 * projectName) throws ClientProtocolException, IOException { HttpUriRequest
	 * request = new HttpGet( "http://localhost:8086//employee//"+employeeName );
	 * 
	 * CloseableHttpResponse response = HttpClientBuilder.create().build().execute(
	 * request ); HttpEntity entity = response.getEntity();
	 * 
	 * String res = null;
	 * 
	 * InputStream instream = entity.getContent();
	 * 
	 * byte[] bytes = IOUtils.toByteArray(instream);
	 * 
	 * res = new String(bytes, "UTF-8");
	 * 
	 * instream.close(); // Long emloyeeId= res.substring(0, 0); //Projects project
	 * = new Projects(null, projectName, null);
	 * 
	 * return null; }
	 */

}
