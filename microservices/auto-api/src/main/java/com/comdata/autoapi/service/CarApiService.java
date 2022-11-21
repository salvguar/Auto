package com.comdata.autoapi.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.comdata.autoservice.DTO.CarDTO;
import com.comdata.autoservice.model.Car;

public interface CarApiService {
	
	public Page<Car> getPage(int page, int items);
	
	public Car getById(UUID id);
	
	public boolean add(CarDTO car);
	
	public boolean edit(CarDTO car);
	
	public boolean delete(CarDTO car);
	
}
