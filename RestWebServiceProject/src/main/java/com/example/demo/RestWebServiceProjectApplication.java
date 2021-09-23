package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.example.demo")
@EntityScan("com.example.demo.entity")
@EnableJpaRepositories(basePackages = "com.example.demo")
@EnableAutoConfiguration
public class RestWebServiceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWebServiceProjectApplication.class, args);
	}

}
