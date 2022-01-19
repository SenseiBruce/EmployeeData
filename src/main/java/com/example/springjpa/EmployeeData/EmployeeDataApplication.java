package com.example.springjpa.EmployeeData;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestTemplate;

import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@ComponentScan({"com.example.springjpa"})
@EnableJpaRepositories("com.example.springjpa.repository") 
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example.springjpa.entity"})
@EnableJms
public class EmployeeDataApplication {
	
	@Value("${spring.datasource.maximum-pool-size}")
	private int connectionPoolSize;


	public static void main(String[] args) {
		SpringApplication.run(EmployeeDataApplication.class, args);
		
	}

	@Bean
	public Scheduler jdbcScheduler() {
		return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
	}

	@Bean
	public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
		return new TransactionTemplate(transactionManager);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	
}
