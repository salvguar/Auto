package com.comdata.auto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comdata.auto.model.Car;
import com.comdata.auto.service.CarService;
import com.comdata.auto.DTO.CarDTO;

@RestController
public class CarController {
	
	private CarService service;
	private ModelMapper modelMapper;
	
	public CarController(CarService carService, ModelMapper modelMapper) {
		this.service = carService;
		this.modelMapper = modelMapper;
	}
	
	@GetMapping("/cars")
	public Page<CarDTO> showAllCar(@RequestParam("page") int page, @RequestParam("items") int items) {
        Page<Car> listCar =service.getPage(page, items);
        List<Car> cars = listCar.toList();
        List<CarDTO> carsDto = new ArrayList<>();
        for(int i=0;i <cars.size(); i++) {
            CarDTO carDto = carToDto(cars.get(i));
            carsDto.add(carDto);            
        }
        Page<CarDTO> pageCarDto= new PageImpl<>(carsDto, PageRequest.of(page, items), Long.valueOf(carsDto.size()) );
        return pageCarDto;
    }
	
	@GetMapping("/car")
	public CarDTO getById(@RequestParam("id") UUID id) {
		return carToDto(service.getById(id));
	}
	
	@PostMapping("/car")
	public boolean add(@Valid @RequestBody CarDTO carDTO) {
		Car car = dtoToCar(carDTO);
		return service.add(car);
		
	}
	
	@PutMapping("/car")
	public boolean edit(@Valid @RequestBody Car car) {
		return service.edit(car);
	}
	
	@DeleteMapping("/car")
	public boolean delete(@RequestBody Car car) {
		return service.delete(car);
	}
	
	public Car dtoToCar(CarDTO carDTO) {
		Car car = this.modelMapper.map(carDTO, Car.class);
		return car;	
	}
	
	public CarDTO carToDto(Car car) {
		CarDTO carDTO = this.modelMapper.map(car, CarDTO.class);
		return carDTO;	
	}
}
