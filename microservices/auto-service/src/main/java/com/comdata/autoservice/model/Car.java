package com.comdata.autoservice.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cars")
public class Car {
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@NotNull
	@Size(min = 2, message = "Enter at least 2 characters.")
	private String brand;
	
	@NotNull
	@Size(min = 2, message = "Enter at least 2 characters.")
	@Column(unique = true)
	private String licensePlate;
	
	public Car() {}
	
	public Car(UUID id, String brand, String licensePlate) {
		this.id = id;
		this.brand = brand;
		this.licensePlate = licensePlate;
	}
	
	public Car(String brand, String licensePlate) {
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
		return "Car [id=" + id + ", brand=" + brand + ", licensePlate=" + licensePlate + "]";
	}
	
	
}
