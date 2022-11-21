package com.comdata.autoworker.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.comdata.autoservice.DTO.CarDTO;
import com.comdata.autoservice.model.Car;
import com.comdata.autoservice.repository.CarRepository;

@Service
public class CarWorkerServiceImpl implements CarWorkerService{
	
	private CarRepository repository;
	private Logger logger = LoggerFactory.getLogger(CarWorkerServiceImpl.class);
	private ModelMapper modelMapper;
	
	public CarWorkerServiceImpl(CarRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}
	
	@KafkaListener(topics = "POST", groupId = "my_group_id")
	public void add(CarDTO car) {
		logger.info("Add " + car.toString());
		add(dtoToCar(car));
	}
	
	@KafkaListener(topics = "PUT", groupId = "my_group_id")
	public void edit(CarDTO car) {
		logger.info("Edit " + car.toString());
		edit(dtoToCar(car));
	}
	
	@KafkaListener(topics = "DELETE", groupId = "my_group_id")
	public void delete(CarDTO car) {
		logger.info("Delete " + car.toString());
		delete(dtoToCar(car));
	}
		
	@Override
	public boolean add(Car car) {
		try {
			logger.info("ADD CAR WITH POST-MAPPING");
			repository.save(car);
			return true;
		}catch (Exception e) {
			logger.error("Incorrect validation!");
			return false;
		}
		
	}

	@Override
	public boolean edit(Car car) {
		try {
			logger.info("EDIT CAR WITH PUT-MAPPING");
			if(repository.findById(car.getId()).isPresent()) {
				repository.save(car);
				return true;
			}
			else
				logger.error("ID is not present!");
				return false;
		}catch (Exception e) {
			logger.error("Incorrect validation!");
			return false;
		}
	}

	@Override
	public boolean delete(Car car) {
		try{
			logger.info("DELETE CAR");
			if(repository.findById(car.getId()).isPresent()) {
				repository.delete(car);
				return true;
			}
			else
				logger.error("ID is not present!");
				return false;
		}catch (Exception e) {
			logger.error("Incorrect validation!");
			return false;
		}
		
	}
	
	public Car dtoToCar(CarDTO carDTO) {
		Car car = this.modelMapper.map(carDTO, Car.class);
		return car;	
	}
	
	

}
