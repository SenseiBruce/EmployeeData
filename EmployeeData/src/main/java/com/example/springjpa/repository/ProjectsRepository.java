package com.example.springjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springjpa.entity.Projects;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {

	List<Projects> findByProjectName(String name);
	
	@Query(nativeQuery=true, value="select count (1) from projects")
	long getCount();
	
	
	@Query(nativeQuery=true, value="select project_name from projects ")
	List<String> findAllProjectNames();

	 
}
