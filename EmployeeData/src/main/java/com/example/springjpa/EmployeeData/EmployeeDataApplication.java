package com.example.springjpa.EmployeeData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@ComponentScan({"com.example.springjpa"})
@EnableJpaRepositories("com.example.springjpa.repository") 
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example.springjpa.entity"})
@EnableJms
public class EmployeeDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDataApplication.class, args);
		
	}

	
	
}
