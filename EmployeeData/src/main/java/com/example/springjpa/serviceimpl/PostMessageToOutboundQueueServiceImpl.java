package com.example.springjpa.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springjpa.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PostMessageToOutboundQueueServiceImpl {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	private static final Logger LOGGER=LoggerFactory.getLogger(PostMessageToOutboundQueueServiceImpl.class);

	
	public Employee sendEmployeeToOutBoundQueue(@RequestBody Employee employee) {
		LOGGER.info("starting");
		try {
			ObjectMapper mapper = new ObjectMapper();
			String studentAsJson = mapper.writeValueAsString(employee);
			LOGGER.info("sending");
			jmsTemplate.convertAndSend("employee_outbound.queue", studentAsJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * LOGGER.
		 * info("Starting to send the employee object to employee_outbound.queue. ");
		 * jmsTemplate.convertAndSend("employee_outbound.queue", new MessageCreator() {
		 * 
		 * 
		 * @Override public Message createMessage(Session session) throws JMSException {
		 * 
		 * return session.createTextMessage(message); } });
		 * LOGGER.info("Sent the employee object to employee_outbound.queue. ");
		 */
		
		return employee;
		
	}
}
