package com.comdata.auto.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.comdata.auto.model.Car;

public interface CarService {
	
	public Page<Car> getPage(int page, int items);
	
	public Car getById(UUID id);
	
	public boolean add(Car car);
	
	public boolean edit(Car car);
	
	public boolean delete(Car car);

	
	
}
