package com.comdata.auto.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.comdata.auto.model.Car;

public interface CarRepository extends PagingAndSortingRepository<Car, UUID>{
	
}
