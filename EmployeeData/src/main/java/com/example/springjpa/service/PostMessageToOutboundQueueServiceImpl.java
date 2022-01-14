package com.example.springjpa.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springjpa.entity.Employee;

@Service
public interface PostMessageToOutboundQueueServiceImpl {

	
	public Employee sendEmployeeToOutBoundQueue(@RequestBody Employee employee);
		
		
	}

