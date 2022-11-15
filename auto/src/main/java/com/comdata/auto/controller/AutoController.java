package com.comdata.auto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comdata.auto.model.Auto;
import com.comdata.auto.DTO.AutoDTO;
import com.comdata.auto.service.AutoServiceImpl;

@RestController
public class AutoController {
	
	@Autowired
	private AutoServiceImpl service;
	
	@GetMapping("/page")
	public Page<Auto> getPage1(@RequestParam("page") int pagina){
		return service.getFirstPage(pagina);
	}
	
	@GetMapping("/show")
	public List<Auto> showAll() {
		return service.showAll();
	}
	
	@GetMapping("/add")
	public AutoDTO add() {
		return service.add();
	}
	
	@PostMapping("/add")
	public AutoDTO add(@Valid @RequestBody Auto auto) {
		return service.add(auto);
	}
	
	@PutMapping("/edit")
	public AutoDTO edit(@Valid @RequestBody Auto auto) {
		return service.edit(auto);
	}
	
	@DeleteMapping("/delete")
	public AutoDTO delete(@RequestBody Auto auto) {
		return service.delete(auto);
	}
}
