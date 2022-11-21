package com.comdata.autoservice.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.comdata.autoservice.model.Car;

public interface CarRepository extends PagingAndSortingRepository<Car, UUID>{
	
}
