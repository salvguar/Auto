package com.comdata.auto.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.comdata.auto.service.CarServiceImpl;

@Service
public class KafkaConsumer {
	
	private Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
	
	@KafkaListener(topics = "my_topic", groupId = "my_group_id")
	public void getMessage(String message) {
		logger.info(message);
		System.out.println(message);	
	}
}
