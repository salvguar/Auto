package com.comdata.autoapi;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.comdata.autoapi.service.CarApiServiceImpl;
import com.comdata.autoservice.DTO.CarDTO;
import com.comdata.autoservice.model.Car;
import com.comdata.autoservice.repository.CarRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CarRepositoryTests {
	
	@InjectMocks
    private CarApiServiceImpl carService;
    
    @MockBean
    private CarRepository carRepo;
    
	@Test
	public void createNewCar() {
		CarDTO car = new CarDTO("Audi","gg643gg");
		carService.add(car);
		assertNotNull(car);		
	}
	
	@Test
	public void getCarById() {
		UUID id = UUID.fromString("5e053f99-9d43-438d-8a23-21fa702a5364");
		Optional<Car> toCompare = Optional.of(new Car(UUID.fromString("5e053f99-9d43-438d-8a23-21fa702a5364"), "hd242hd", "range rover"));
		when(carRepo.findById(id)).thenReturn(toCompare);
		Car cars = toCompare.get();
		assertTrue(cars.equals(carService.getById(id)));
	}
	
	
}
