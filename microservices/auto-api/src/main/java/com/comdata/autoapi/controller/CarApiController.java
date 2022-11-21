package com.comdata.autoapi.controller;

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
import com.comdata.autoapi.service.CarApiService;
import com.comdata.autoservice.DTO.CarDTO;
import com.comdata.autoservice.model.Car;

@RestController
public class CarApiController {
	
	private CarApiService service;
	private ModelMapper modelMapper;
	
	public CarApiController(CarApiService carService, ModelMapper modelMapper) {
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
	public boolean add(@Valid @RequestBody CarDTO car) {
		return service.add(car);
		
	}
	
	@PutMapping("/car")
	public boolean edit(@Valid @RequestBody CarDTO car) {
		return service.edit(car);
	}
	
	@DeleteMapping("/car")
	public boolean delete(@RequestBody CarDTO car) {
		return service.delete(car);
	}
	
	public CarDTO carToDto(Car car) {
		CarDTO carDTO = this.modelMapper.map(car, CarDTO.class);
		return carDTO;	
	}
}
