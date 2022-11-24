package com.comdata.autoservice.DTO;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class CarDTO {
	
	@Id
	private UUID id;
	
	@NotNull
	@Size(min = 2, message = "Enter at least 2 characters.")
	private String brand;
	
	@NotNull
	@Size(min = 2, message = "Enter at least 2 characters.")
	@Column(unique = true)
	private String licensePlate;
	
	public CarDTO() {}
	
	public CarDTO(UUID id, String brand, String licensePlate) {
		super();
		this.id = id;
		this.brand = brand;
		this.licensePlate = licensePlate;
	}
	
	public CarDTO(String brand, String licensePlate) {
		this.brand = brand;
		this.licensePlate = licensePlate;
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
	
	public String getLicensePlate() {
		return this.licensePlate;
	}
	
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	@Override
	public String toString() {
		return "CarDTO [id=" + id + ", brand=" + brand + ", licensePlate=" + licensePlate + "]";
	}

	
	
}
