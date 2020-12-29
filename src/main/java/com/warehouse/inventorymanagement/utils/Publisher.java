package com.warehouse.inventorymanagement.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.warehouse.inventorymanagement.model.ProductMessage;

@Service
public class Publisher {
	
	private static final Logger logger = LoggerFactory.getLogger(Publisher.class);
	private static final String TOPIC = "inventory";
	
	@Autowired
	private KafkaTemplate<String, ProductMessage> kafkaTemplate;

	public void sendMessage(ProductMessage message) {
		logger.info(String.format("$$ -> Producing message --> %s", message));
		this.kafkaTemplate.send(TOPIC, message);
	}
	
	
}
