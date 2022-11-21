package com.comdata.autoapi.service;

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

@Service
public class CarApiServiceImpl implements CarApiService{
	
	private CarRepository repository;
	private Logger logger = LoggerFactory.getLogger(CarApiServiceImpl.class);

	private static final String POST = "POST";
	private static final String PUT = "PUT";
	private static final String DELETE = "DELETE";
	@Autowired
	private KafkaTemplate<String, CarDTO> kafkatemplate;
	
	
	public CarApiServiceImpl(CarRepository repository) {
		this.repository = repository;
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
	public boolean add(CarDTO car) {
		try {
			logger.info("ADD CAR WITH POST-MAPPING");
			this.kafkatemplate.send(POST, car);
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
			this.kafkatemplate.send(PUT, car);
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
			this.kafkatemplate.send(DELETE, car);
			return true;
		}catch (Exception e) {
			logger.error("Incorrect validation!");
			return false;
		}
	}
	
}
