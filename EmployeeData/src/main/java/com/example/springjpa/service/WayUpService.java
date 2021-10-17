package com.example.springjpa.service;

import java.util.List;

import com.example.springjpa.entity.WayUp;

public interface WayUpService {
	
	
	List<WayUp> getAll();

	List<WayUp> getByName(String name);


}
