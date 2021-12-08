package com.example.springjpa.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class SpringActiveMQConfig {
	
	@Value("tcp://localhost:61616")
	private String brokerUrl;
	
	@Bean
	public ActiveMQQueue queue() {
		return new ActiveMQQueue("javainuse");
	}
	
	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		
		ActiveMQConnectionFactory activeMQConnectionFactory = new  ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		return activeMQConnectionFactory;
	}
	
	@Bean
	JmsTemplate jmsTemplate() {
		return new JmsTemplate(activeMQConnectionFactory());
	}
	
}
