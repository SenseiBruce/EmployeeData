package com.example.springjpa.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springjpa.entity.WayUp;
import com.example.springjpa.repository.WayUpRepository;
import com.example.springjpa.service.WayUpService;

@Service
public class WayUpServiceImpl implements WayUpService {

	@Autowired
	WayUpRepository wayUpRepository;
	
	
	

	@Override
	public List<WayUp> getByName(String name) {
		
		return wayUpRepository.findByName(name);
	}




	@Override
	public List<WayUp> getAll() {
		
		return wayUpRepository.findAll();
	}
	
}
