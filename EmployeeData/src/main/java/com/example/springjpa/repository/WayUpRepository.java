package com.example.springjpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springjpa.entity.WayUp;

@Repository
public interface WayUpRepository extends JpaRepository<WayUp, Long> {

	
	//List<WayUp> findOne();
	Optional<WayUp> findById(Long Id);
	
	List<WayUp> findByName(String name);
	
	List<WayUp> findAll();
}
