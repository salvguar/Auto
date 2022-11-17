package com.comdata.auto.service;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.comdata.auto.model.Car;
import com.comdata.auto.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{
	
	private CarRepository repository;
	private Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
	
	public CarServiceImpl(CarRepository repository) {
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

}
