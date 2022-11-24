package com.comdata.autoapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comdata.autoapi.service.UserService;
import com.comdata.autoapi.utility.UserLogin;
import com.comdata.autoservice.model.User;

@RestController
public class UserController {
	
	private UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<Object> login(@RequestBody @Valid UserLogin userLogin){
		return service.login(userLogin);
	}
	
	@PostMapping("/auth/login/register")
	public boolean createUser(@RequestBody @Valid User user) {
		return service.createUser(user);
	}
	
		
}
