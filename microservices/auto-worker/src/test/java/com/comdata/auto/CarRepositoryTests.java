package com.comdata.auto;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.comdata.autoservice.model.Car;
import com.comdata.autoservice.repository.CarRepository;
import com.comdata.autoworker.service.CarWorkerServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CarRepositoryTests {
	
	@InjectMocks
    private CarWorkerServiceImpl carService;
    
    @MockBean
    private CarRepository carRepo;
    
	@Test
	public void testCreate() {
		UUID id = UUID.randomUUID();
		Car newCar = new Car(id, "fc006zc", "fiat");
		when(carRepo.save(newCar)).thenReturn(new Car (id,"fc006zc", "fiat"));
		assertTrue(carService.add(newCar));
	}
	
	@Test
	public void testDelete() {
		UUID id = UUID.fromString("5e053f99-9d43-438d-8a23-21fa702a5364");
		Optional<Car> carCompare = Optional.of(new Car(UUID.fromString("5e053f99-9d43-438d-8a23-21fa702a5364"), "hd242hd", "range rover"));
		Car car = carCompare.get();
		when(carRepo.findById(id)).thenReturn(carCompare);
		assertTrue(carService.delete(car));		
	}
	
	
}
