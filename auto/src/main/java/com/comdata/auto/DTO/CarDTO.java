package com.comdata.auto.DTO;

import java.util.UUID;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CarDTO {
	
	private UUID id;
	
	@NotNull
	@Size(min = 2, message = "Enter at least 2 characters.")
	private String brand;
	
	@NotNull
	@Size(min = 2, message = "Enter at least 2 characters.")
	@Column(unique = true)
	private String licensePlate;
	
	public CarDTO() {}
	
	public CarDTO(String brand, String licensePlate) {
		setBrand(brand);
		setlicensePlate(licensePlate);
	}
	
	public UUID getId() {
		return this.id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getlicensePlate() {
		return this.licensePlate;
	}
	
	public void setlicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
}
