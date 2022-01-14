package com.example.springjpa.EmployeeData;



import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class Listener {
	
	
	private static final Logger LOGGER=LoggerFactory.getLogger(Listener.class);
	 
	

	@JmsListener(destination = "employee_inbound.queue")
	@SendTo("employee_outbound.queue")
	public String receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		LOGGER.info("Received message " + jsonMessage);
		String response = null;
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			LOGGER.info("Received message " + messageData);
			Map map = new Gson().fromJson(messageData, Map.class);
			response  = "Hello " + map.get("name");
		}
		return response;
	}

}