package com.comdata.autoapi.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.comdata.autoservice.DTO.CarDTO;
import com.comdata.autoservice.model.Car;
import com.comdata.autoservice.repository.CarRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CarApiServiceImpl implements CarApiService{
	
	private CarRepository repository;
	private Logger logger = LoggerFactory.getLogger(CarApiServiceImpl.class);
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final String TOPIC = "my_topic";	
	private KafkaTemplate<String, Map> kafkaMap;
	
	public CarApiServiceImpl(CarRepository repository, KafkaTemplate<String, Map> kafkaMap) {
		this.repository = repository;
		this.kafkaMap = kafkaMap;
	}
	
	public Page<Car> getPage(int page, int items){
		try {
			return repository.findAll(PageRequest.of(page, items));
		}catch (Exception e) {
			return null;
		}	
	}
	
	@Override
	public Car getById(UUID id) {
		try {
			Optional<Car> car = repository.findById(id);
			if(car.isPresent())
				return car.get();
			throw new Exception();
		}catch (Exception e) {
			return new Car();
		}
	}


	@Override
	public boolean add(CarDTO car){
		try {
			logger.info("ADD CAR WITH POST-MAPPING");
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("OperationType", "CREATE");
			String carJson = OBJECT_MAPPER.writeValueAsString(car);
			headers.put("entity", carJson);
			this.kafkaMap.send(TOPIC, headers);
			return true;
		}catch (Exception e) {
			logger.error("Incorrect validation!");
			return false;
		}
	}


	@Override
	public boolean edit(CarDTO car) {
		try {
			logger.info("EDIT CAR WITH PUT-MAPPING");
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("OperationType", "UPDATE");
			String carJson = OBJECT_MAPPER.writeValueAsString(car);
			headers.put("entity", carJson);
			this.kafkaMap.send(TOPIC, headers);
			return true;
		}catch (Exception e) {
			logger.error("Incorrect validation!");
			return false;
		}
	}


	@Override
	public boolean delete(CarDTO car) {
		try {
			logger.info("DELETE CAR");
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("OperationType", "DELETE");
			String carJson = OBJECT_MAPPER.writeValueAsString(car);
			headers.put("entity", carJson);
			this.kafkaMap.send(TOPIC, headers);
			return true;
		}catch (Exception e) {
			logger.error("Incorrect validation!");
			return false;
		}
	}
	
}
