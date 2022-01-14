package com.example.springjpa.EmployeeData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
<<<<<<< HEAD
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
		
=======
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan({"com.example.springjpa"})
@EnableJpaRepositories("com.example.springjpa.repository") 
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example.springjpa.entity"})
@RestController
//@EnableOAuth2Client
public class EmployeeDataApplication {
	/*
	 * @GetMapping("/") public String welcome2User(Principal principal) { return
	 * "Hi "+principal.getName()+" Welcome to javatechie"; }
	 */

	//private static  ApplicationContext  ctx;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeDataApplication.class, args);
		//SpringApplication.run(EmployeeDataApplication.class, args);
		//JmsTemplate jms = SpringApplication.run(EmployeeDataApplication.class, args).getBean(JmsTemplate.class);
		//jms.convertAndSend("javainuse", "test message");
>>>>>>> branch 'master' of https://github.com/SenseiBruce/EmployeeData.git
	}

	
	
}
