package com.example.springjpa.EmployeeData;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class Producer {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(Producer.class);
	 
	

	@Autowired
	JmsTemplate jmsTemplate;

	public void sendMessage(final String queueName, final String message) {
		Map map = new Gson().fromJson(message, Map.class);
		final String textMessage = "Hello" + map.get("name");
		LOGGER.info("Sending message " + textMessage + "to queue - " + queueName);
		jmsTemplate.send(queueName, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				return message;
			}
		});
	}

}