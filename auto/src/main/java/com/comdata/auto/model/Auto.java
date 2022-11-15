package com.comdata.auto.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="auto")
public class Auto {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@NotNull
	@Size(min = 2, message = "inserisci almeno due caratteri.")
	private String marca;
	
	@NotNull
	@Size(min = 2, message = "inserisci almeno due caratteri.")
	@Column(unique = true)
	private String targa;
	
	public Auto(String marca, String targa) {
		this.id = UUID.randomUUID();
		this.marca = marca;
		this.targa = targa;
	}
}
