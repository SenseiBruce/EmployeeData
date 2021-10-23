package com.example.springjpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springjpa.repository.EmployeeAccountRepository;

@Service
public interface EmployeeAccountService {

	List<EmployeeAccountRepository> getAll();

}
